package com.gpcf.BookMyShow.Repository;

import com.gpcf.BookMyShow.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByLanguage(String language);

    List<Movie> findByGenre(String genre);

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
