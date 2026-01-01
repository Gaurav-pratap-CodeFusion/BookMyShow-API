package com.gpcf.BookMyShow.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatDto {

    private Long id;
    private SeatDto seat;
    private String status;   // AVAILABLE, LOCKED, BOOKED
    private Double price;
}
