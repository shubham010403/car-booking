package com.example.car_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResDto {
    private Long id;

    private String model;
    private String brand;
    private boolean availability;
    private double rentalPrice;
}
