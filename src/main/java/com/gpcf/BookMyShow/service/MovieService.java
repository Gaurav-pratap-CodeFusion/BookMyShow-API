package com.gpcf.BookMyShow.service;

import com.gpcf.BookMyShow.dto.MovieDto;
import com.gpcf.BookMyShow.Exception.ResourceNotFoundException;
import com.gpcf.BookMyShow.model.Movie;
import com.gpcf.BookMyShow.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieDto createMovie(MovieDto dto) {
        Movie movie = mapToEntity(dto);
        return mapToDto(movieRepository.save(movie));
    }

    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return mapToDto(movie);
    }

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguage(language)
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre)
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> searchMovies(String keyword) {
        return movieRepository.findByTitleContainingIgnoreCase(keyword)
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private MovieDto mapToDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getLanguage(),
                movie.getGenre(),
                movie.getDurationMins(),
                movie.getReleaseDate(),
                movie.getPosterUrl()
        );
    }

    private Movie mapToEntity(MovieDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setLanguage(dto.getLanguage());
        movie.setGenre(dto.getGenre());
        movie.setDurationMins(dto.getDurationMins());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setPosterUrl(dto.getPosterUrl());
        return movie;
    }
}
