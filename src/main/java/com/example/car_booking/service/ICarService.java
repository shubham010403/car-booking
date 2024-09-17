package com.example.car_booking.service;

import com.example.car_booking.dto.CarDto;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.User;

import java.util.List;

public interface ICarService {
    ResponseModel<Car> addCar(CarDto car);
    ResponseModel<Car> updateCar(Long id, CarDto car);
    ResponseModel<Void> deleteCar(Long id);
    ResponseModel<List<User>> getAllUsers();
}
