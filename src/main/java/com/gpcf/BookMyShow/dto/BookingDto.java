package com.gpcf.BookMyShow.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Long id;
    private String bookingNumber;
    private LocalDateTime bookingTime;
    private UserDto user;
    private ShowDto show;
    private String status;        // CONFIRMED, CANCELLED
    private Double totalAmount;
    private List<ShowSeatDto> seats;
    private PaymentDto payment;
}
