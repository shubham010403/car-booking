package com.example.car_booking.controller;

import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ICarService carService;

    // GET /cars - Browse available cars (User access)
    @GetMapping("/cars")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseModel<List<Car>>> getAvailableCars() {
        ResponseModel<List<Car>> response = carService.getAvailableCars();
        return new ResponseEntity<>(response, response.getStatus());
    }

    // POST /book/{carId} - Book a car (User access)
    @PostMapping("/book/{carId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseModel<Booking>> bookCar(@PathVariable Long carId, @RequestBody Booking booking) {
        ResponseModel<Booking> response = carService.bookCar(carId, booking.getUser().getId());
        return new ResponseEntity<>(response, response.getStatus());
    }

    // GET /bookings - View the user's bookings (User access)
    @GetMapping("/bookings/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseModel<List<Booking>>> getUserBookings(@PathVariable Long userId) {
        ResponseModel<List<Booking>> response = carService.getUserBookings(userId);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
