package com.gpcf.BookMyShow.Controller;

import com.gpcf.BookMyShow.dto.MovieDto;
import com.gpcf.BookMyShow.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "Movies APIs")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @Operation(summary = "Add movie (Admin)")
    public ResponseEntity<MovieDto> createMovie(
            @Valid @RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(
                movieService.createMovie(movieDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping
    @Operation(summary = " Get all movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }


    @GetMapping("/language/{language}")
    @Operation(summary = "Filter by language")
    public ResponseEntity<List<MovieDto>> getByLanguage(
            @PathVariable String language) {
        return ResponseEntity.ok(movieService.getMoviesByLanguage(language));
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Filter by genre")
    public ResponseEntity<List<MovieDto>> getByGenre(
            @PathVariable String genre) {
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }

    @GetMapping("/search")
    @Operation(summary = "Search movie")
    public ResponseEntity<List<MovieDto>> search(
            @RequestParam String keyword) {
        return ResponseEntity.ok(movieService.searchMovies(keyword));
    }
}
