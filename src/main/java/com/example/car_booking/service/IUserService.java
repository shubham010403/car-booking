package com.example.car_booking.service;

import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.ResponseModel;

import java.util.List;

public interface IUserService {
    ResponseModel<List<Car>> getAvailableCars();
    ResponseModel<Booking> bookCar(Long carId);
    ResponseModel<List<Booking>> getUserBookings(Long userId);

}
