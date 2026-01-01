package com.gpcf.BookMyShow.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {

    private Long id;
    private String seatNumber;
    private String seatType;   // GOLD, SILVER, PLATINUM
    private Double basePrice;
}
