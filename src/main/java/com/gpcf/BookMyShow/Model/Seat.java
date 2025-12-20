package com.gpcf.BookMyShow.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name="seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //table

    private String seatNumber;

    private String seatType;

    private Double basePrice;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
}
