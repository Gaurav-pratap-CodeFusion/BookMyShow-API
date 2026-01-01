package com.gpcf.BookMyShow.service;

import com.gpcf.BookMyShow.Exception.ResourceNotFoundException;
import com.gpcf.BookMyShow.Repository.MovieRepository;
import com.gpcf.BookMyShow.Repository.ScreenRepository;
import com.gpcf.BookMyShow.Repository.ShowRepository;
import com.gpcf.BookMyShow.Repository.ShowSeatRepository;
import com.gpcf.BookMyShow.dto.*;
import com.gpcf.BookMyShow.enums.ShowSeatStatus;
import com.gpcf.BookMyShow.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    @Transactional
    public ShowDto createShow(ShowDto dto) {

        Movie movie = movieRepository.findById(dto.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        Screen screen = screenRepository.findById(dto.getScreen().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found"));

        boolean overlapExists =
                showRepository.existsByScreen_IdAndStartTimeLessThanAndEndTimeGreaterThan(
                        screen.getId(),
                        dto.getEndTime(),
                        dto.getStartTime()
                );

        if (overlapExists) {
            throw new IllegalStateException("Show already exists for this screen in given time slot");
        }

        Show show = Show.builder()
                .movie(movie)
                .screen(screen)
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();

        Show savedShow = showRepository.save(show);

        List<ShowSeat> showSeats = screen.getSeats().stream()
                .map(seat -> ShowSeat.builder()
                        .show(savedShow)
                        .seat(seat)
                        .price(seat.getBasePrice())
                        .status(ShowSeatStatus.AVAILABLE)
                        .build())
                .collect(Collectors.toList());

        showSeatRepository.saveAll(showSeats);

        return mapToDto(savedShow, showSeats);
    }

    public ShowDto getShowById(Long id) {

        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        List<ShowSeat> availableSeats =
                showSeatRepository.findByShow_IdAndStatus(
                        id, ShowSeatStatus.AVAILABLE
                );

        return mapToDto(show, availableSeats);
    }

    public List<ShowDto> getAllShows() {

        return showRepository.findAll().stream()
                .map(show -> mapToDto(
                        show,
                        showSeatRepository.findByShow_IdAndStatus(
                                show.getId(), ShowSeatStatus.AVAILABLE
                        )
                ))
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowsByMovie(Long movieId) {

        return showRepository.findByMovieId(movieId).stream()
                .map(show -> mapToDto(
                        show,
                        showSeatRepository.findByShow_IdAndStatus(
                                show.getId(), ShowSeatStatus.AVAILABLE
                        )
                ))
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowsByMovieAndCity(Long movieId, String city) {

        return showRepository
                .findByMovie_IdAndScreen_Theater_City(movieId, city)
                .stream()
                .map(show -> mapToDto(
                        show,
                        showSeatRepository.findByShow_IdAndStatus(
                                show.getId(), ShowSeatStatus.AVAILABLE
                        )
                ))
                .collect(Collectors.toList());
    }

    private ShowDto mapToDto(Show show, List<ShowSeat> availableSeats) {

        ShowDto showDto = new ShowDto();
        showDto.setId(show.getId());
        showDto.setStartTime(show.getStartTime());
        showDto.setEndTime(show.getEndTime());

        showDto.setMovie(new MovieDto(
                show.getMovie().getId(),
                show.getMovie().getTitle(),
                show.getMovie().getDescription(),
                show.getMovie().getLanguage(),
                show.getMovie().getGenre(),
                show.getMovie().getDurationMins(),
                show.getMovie().getReleaseDate(),
                show.getMovie().getPosterUrl()
        ));

        TheaterDto theaterDto = new TheaterDto(
                show.getScreen().getTheater().getId(),
                show.getScreen().getTheater().getName(),
                show.getScreen().getTheater().getAddress(),
                show.getScreen().getTheater().getCity(),
                show.getScreen().getTheater().getTotalScreens()
        );

        showDto.setScreen(new ScreenDto(
                show.getScreen().getId(),
                show.getScreen().getName(),
                show.getScreen().getTotalSeats(),
                theaterDto
        ));

        List<ShowSeatDto> seatDtos = availableSeats.stream()
                .map(seat -> new ShowSeatDto(
                        seat.getId(),
                        new SeatDto(
                                seat.getSeat().getId(),
                                seat.getSeat().getSeatNumber(),
                                seat.getSeat().getSeatType().name(),
                                seat.getSeat().getBasePrice()
                        ),
                        seat.getStatus().name(),
                        seat.getPrice()
                ))
                .collect(Collectors.toList());

        showDto.setAvailableSeats(seatDtos);

        return showDto;
    }


    public List<ShowDto> getShowsByDateRange(
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {

        return showRepository
                .findByStartTimeBetween(startDate, endDate)
                .stream()
                .map(show -> mapToDto(
                        show,
                        showSeatRepository.findByShow_IdAndStatus(
                                show.getId(),
                                ShowSeatStatus.AVAILABLE
                        )
                ))
                .collect(Collectors.toList());
    }

}
