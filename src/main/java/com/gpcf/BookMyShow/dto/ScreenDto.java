package com.gpcf.BookMyShow.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreenDto {

    private Long id;
    private String name;
    private Integer totalSeats;
    private TheaterDto theater;
}
