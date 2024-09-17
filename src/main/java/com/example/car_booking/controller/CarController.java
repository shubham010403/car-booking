package com.example.car_booking.controller;

import com.example.car_booking.dto.CarDto;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.User;
import com.example.car_booking.service.ICarService;
import com.example.car_booking.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private ICarService carService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // POST /cars - Add a new car to the fleet
    @PostMapping("/cars")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseModel<Car>> addCar(@RequestBody CarDto car) {
        ResponseModel<Car> response = carService.addCar(car);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // PUT /cars/{id} - Update car details (e.g., availability)
    @PutMapping("/cars/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseModel<Car>> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        ResponseModel<Car> response = carService.updateCar(id, carDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // DELETE /cars/{id} - Remove a car from the fleet
    @DeleteMapping("/cars/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseModel<Void>> deleteCar(@PathVariable Long id) {
        ResponseModel<Void> response = carService.deleteCar(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseModel<List<User>>> getAllUsers() {
        ResponseModel<List<User>> response = carService.getAllUsers();
        return new ResponseEntity<>(response, response.getStatus());
    }
}
