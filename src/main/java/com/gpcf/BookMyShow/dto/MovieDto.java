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



    public MovieDto(Long id, String title, String language,String genre,String description) {
        this.id=id;
        this.title=title;
        this.language=language;
        this.genre=genre;
        this.description=description;

    }
}
