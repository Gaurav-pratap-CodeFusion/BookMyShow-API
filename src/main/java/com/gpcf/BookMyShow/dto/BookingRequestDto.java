package com.gpcf.BookMyShow.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    private Long userId;
    private Long showId;
    private List<Long> seatIds;
    private String paymentMethod; // UPI, CARD
}
