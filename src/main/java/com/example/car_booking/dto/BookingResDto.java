package com.example.car_booking.dto;

import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResDto {
    private Long id;
    private User user;
    private Car car;
    private LocalDate bookingDate;
    private LocalDate returnDate;
}
