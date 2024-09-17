package com.example.car_booking.service;

import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.User;

import java.util.List;

public interface ICarService {
    ResponseModel<Car> addCar(Car car);
    ResponseModel<Car> updateCar(Long id, Car car);
    ResponseModel<Void> deleteCar(Long id);
    ResponseModel<List<Car>> getAllCars();
    ResponseModel<List<User>> getAllUsers();
    ResponseModel<List<Car>> getAvailableCars();
    ResponseModel<Booking> bookCar(Long carId, Long userId);
    ResponseModel<List<Booking>> getUserBookings(Long userId);
    String addUser(User user);
}
