package com.gpcf.BookMyShow.Controller;

import com.gpcf.BookMyShow.dto.BookingDto;
import com.gpcf.BookMyShow.dto.BookingRequestDto;
import com.gpcf.BookMyShow.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking APIs", description = "you can book, get and Cancel your show using these APIs")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @Operation(summary = "Create Booking")
    public ResponseEntity<BookingDto> createBooking(
            @Valid @RequestBody BookingRequestDto bookingRequest) {
        return new ResponseEntity<>(
                bookingService.createBooking(bookingRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking by ID")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel booking")
    public ResponseEntity<BookingDto> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }
}
