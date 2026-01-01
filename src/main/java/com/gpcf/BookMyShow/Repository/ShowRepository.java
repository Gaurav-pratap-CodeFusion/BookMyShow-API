package com.gpcf.BookMyShow.Repository;

import com.gpcf.BookMyShow.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    // Movie wise shows
    List<Show> findByMovieId(Long movieId);

    // Movie + City wise shows
    List<Show> findByMovie_IdAndScreen_Theater_City(Long movieId, String city);

    // Date range
    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    // 🔥 Overlapping show check (VERY IMPORTANT)
    boolean existsByScreen_IdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long screenId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );


}
