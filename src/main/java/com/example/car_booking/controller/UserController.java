package com.example.car_booking.controller;

import com.example.car_booking.dto.BookingResDto;
import com.example.car_booking.dto.CarResDto;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.dto.BookingDto;
import com.example.car_booking.service.ICarService;
import com.example.car_booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService userService;

    // GET /cars - Browse available cars (User access)
    @GetMapping("/cars")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseModel<List<CarResDto>>> getAvailableCars() {
        ResponseModel<List<CarResDto>> response = userService.getAvailableCars();
        return new ResponseEntity<>(response, response.getStatus());
    }

    // POST /book/{carId} - Book a car (User access)
    @PostMapping("/book/{carId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseModel<BookingResDto>> bookCar(@PathVariable Long carId) {
        ResponseModel<BookingResDto> response = userService.bookCar(carId);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // GET /bookings - View the user's bookings (User access)
    @GetMapping("/bookings/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseModel<List<BookingResDto>>> getUserBookings(@PathVariable Long userId) {
        ResponseModel<List<BookingResDto>> response = userService.getUserBookings(userId);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
