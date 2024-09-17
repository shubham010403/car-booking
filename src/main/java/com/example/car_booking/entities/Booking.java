package com.example.car_booking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user-id",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "car-id",nullable = false)
    private Car  car;
    private LocalDate bookingDate;
    private LocalDate returnDate;


}
