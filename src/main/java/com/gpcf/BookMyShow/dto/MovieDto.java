package com.gpcf.BookMyShow.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private String description;
    private String language;
    private String genre;
    private Integer durationMins;
    private String releaseDate;
    private String posterUrl;
}
