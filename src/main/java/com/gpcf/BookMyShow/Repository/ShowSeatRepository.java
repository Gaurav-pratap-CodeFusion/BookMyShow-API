package com.gpcf.BookMyShow.Repository;

import com.gpcf.BookMyShow.enums.ShowSeatStatus;
import com.gpcf.BookMyShow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShow_IdAndStatus(Long showId, ShowSeatStatus status);

    List<ShowSeat> findByIdInAndShow_Id(List<Long> ids, Long showId);

    List<ShowSeat> findByBooking_Id(Long bookingId);
}
