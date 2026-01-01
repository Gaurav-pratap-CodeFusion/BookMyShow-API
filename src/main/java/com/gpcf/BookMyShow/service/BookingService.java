package com.gpcf.BookMyShow.service;

import com.gpcf.BookMyShow.dto.*;
import com.gpcf.BookMyShow.enums.*;
import com.gpcf.BookMyShow.Exception.*;
import com.gpcf.BookMyShow.model.*;
import com.gpcf.BookMyShow.Repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public BookingDto createBooking(BookingRequestDto request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        List<ShowSeat> seats =
                showSeatRepository.findByIdInAndShow_Id(
                        request.getSeatIds(),
                        show.getId()
                );

        if (seats.size() != request.getSeatIds().size()) {
            throw new SeatUnavailableException("Invalid seat selection");
        }

        for (ShowSeat seat : seats) {
            if (seat.getStatus() != ShowSeatStatus.AVAILABLE) {
                throw new SeatUnavailableException(
                        "Seat " + seat.getSeat().getSeatNumber() + " not available"
                );
            }
            seat.setStatus(ShowSeatStatus.LOCKED);
        }
        showSeatRepository.saveAll(seats);

        double totalAmount = seats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();

        Payment payment = new Payment();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setAmount(totalAmount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(
                PaymentMethod.valueOf(request.getPaymentMethod())
        );
        payment.setStatus(PaymentStatus.SUCCESS);

        Booking booking = new Booking();
        booking.setBookingNumber(UUID.randomUUID().toString());
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setTotalAmount(totalAmount);
        booking.setUser(user);
        booking.setShow(show);
        booking.setPayment(payment);

        Booking savedBooking = bookingRepository.save(booking);

        seats.forEach(seat -> {
            seat.setStatus(ShowSeatStatus.BOOKED);
            seat.setBooking(savedBooking);
        });
        showSeatRepository.saveAll(seats);

        return mapToBookingDto(savedBooking, seats);
    }

    public BookingDto getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        List<ShowSeat> seats =
                showSeatRepository.findByBooking_Id(booking.getId());

        return mapToBookingDto(booking, seats);
    }

    @Transactional
    public BookingDto cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);

        List<ShowSeat> seats =
                showSeatRepository.findByBooking_Id(booking.getId());

        seats.forEach(seat -> {
            seat.setStatus(ShowSeatStatus.AVAILABLE);
            seat.setBooking(null);
        });

        if (booking.getPayment() != null) {
            booking.getPayment().setStatus(PaymentStatus.REFUNDED);
        }

        bookingRepository.save(booking);
        showSeatRepository.saveAll(seats);

        return mapToBookingDto(booking, seats);
    }

    private BookingDto mapToBookingDto(Booking booking, List<ShowSeat> seats) {

        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setBookingNumber(booking.getBookingNumber());
        dto.setBookingTime(booking.getBookingTime());
        dto.setStatus(booking.getStatus().name());
        dto.setTotalAmount(booking.getTotalAmount());

        dto.setUser(new UserDto(
                booking.getUser().getId(),
                booking.getUser().getName(),
                booking.getUser().getEmail(),
                booking.getUser().getPhoneNumber()
        ));

        Show show = booking.getShow();
        dto.setShow(new ShowDto(
                show.getId(),
                show.getStartTime(),
                show.getEndTime(),
                null,
                null,
                null
        ));

        List<ShowSeatDto> seatDtos = seats.stream()
                .map(seat -> new ShowSeatDto(
                        seat.getId(),
                        new SeatDto(
                                seat.getSeat().getId(),
                                seat.getSeat().getSeatNumber(),
                                seat.getSeat().getSeatType().name(),
                                seat.getSeat().getBasePrice()
                        ),
                        seat.getStatus().name(),
                        seat.getPrice()
                ))
                .collect(Collectors.toList());

        dto.setSeats(seatDtos);

        if (booking.getPayment() != null) {
            dto.setPayment(new PaymentDto(
                    booking.getPayment().getId(),
                    booking.getPayment().getTransactionId(),
                    booking.getPayment().getAmount(),
                    booking.getPayment().getPaymentTime(),
                    booking.getPayment().getPaymentMethod().name(),
                    booking.getPayment().getStatus().name()
            ));
        }

        return dto;
    }
}
