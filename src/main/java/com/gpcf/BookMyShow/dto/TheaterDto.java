package com.gpcf.BookMyShow.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {

    private Long id;
    private String name;
    private String address;
    private String city;
    private Integer totalScreens;
}
