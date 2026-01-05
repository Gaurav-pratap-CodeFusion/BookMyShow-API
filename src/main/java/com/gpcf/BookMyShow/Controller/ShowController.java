package com.gpcf.BookMyShow.Controller;

import com.gpcf.BookMyShow.dto.ShowDto;
import com.gpcf.BookMyShow.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
@Tag(name = "Shows APIs")
public class ShowController {

    private final ShowService showService;


    @PostMapping
    @Operation(summary = "Create Show (ADMIN)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShowDto> createShow(
            @Valid @RequestBody ShowDto showDto) {

        return new ResponseEntity<>(
                showService.createShow(showDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get show by ID (Seat availability included)")
    public ResponseEntity<ShowDto> getShowById(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @GetMapping
    @Operation(summary = "Get all shows")
    public ResponseEntity<List<ShowDto>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }


    @GetMapping("/movie/{movieId}")
    @Operation(summary = "Get shows by Movie")
    public ResponseEntity<List<ShowDto>> getShowsByMovie(
            @PathVariable Long movieId) {

        return ResponseEntity.ok(
                showService.getShowsByMovie(movieId)
        );
    }

    @GetMapping("/movie/{movieId}/city/{city}")
    @Operation(summary = "Get shows by Movie + City (MOST USED API)")
    public ResponseEntity<List<ShowDto>> getShowsByMovieAndCity(
            @PathVariable Long movieId,
            @PathVariable String city) {

        return ResponseEntity.ok(
                showService.getShowsByMovieAndCity(movieId, city)
        );
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get shows by Date Range")
    public ResponseEntity<List<ShowDto>> getShowsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {

        return ResponseEntity.ok(
                showService.getShowsByDateRange(startDate, endDate)
        );
    }
}
