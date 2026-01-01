package com.gpcf.BookMyShow.Controller;

import com.gpcf.BookMyShow.Exception.SeatUnavailableException;
import com.gpcf.BookMyShow.dto.TheaterDto;
import com.gpcf.BookMyShow.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
@Tag(name = "Theaters APIs")
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping
    @Operation(summary = "Add Theaters")
    public TheaterDto create(@RequestBody TheaterDto dto) {
        return theaterService.createTheater(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get theater by id")
    public TheaterDto getById(@PathVariable Long id) {
        return theaterService.getTheaterById(id);
    }

    @GetMapping
    @Operation(summary = "Get all theaters")
    public List<TheaterDto> getAll() {
        return theaterService.getAllTheaters();
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get theaters by City")
    public List<TheaterDto> getByCity(@PathVariable String city) {
        return theaterService.getTheatersByCity(city);
    }
}
