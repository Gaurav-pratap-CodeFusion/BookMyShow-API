package com.gpcf.BookMyShow.service;

import com.gpcf.BookMyShow.dto.TheaterDto;
import com.gpcf.BookMyShow.Exception.ResourceNotFoundException;
import com.gpcf.BookMyShow.model.Theater;
import com.gpcf.BookMyShow.Repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public TheaterDto createTheater(TheaterDto dto) {
        Theater theater = mapToEntity(dto);
        return mapToDto(theaterRepository.save(theater));
    }

    public TheaterDto getTheaterById(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found"));
        return mapToDto(theater);
    }

    public List<TheaterDto> getAllTheaters() {
        return theaterRepository.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TheaterDto> getTheatersByCity(String city) {
        return theaterRepository.findByCity(city)
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TheaterDto mapToDto(Theater theater) {
        return new TheaterDto(
                theater.getId(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getTotalScreens()
        );
    }

    private Theater mapToEntity(TheaterDto dto) {
        Theater theater = new Theater();
        theater.setName(dto.getName());
        theater.setAddress(dto.getAddress());
        theater.setCity(dto.getCity());
        theater.setTotalScreens(dto.getTotalScreens());
        return theater;
    }
}
