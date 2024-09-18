package com.example.car_booking.service;

import com.example.car_booking.dto.CarDto;
import com.example.car_booking.dto.CarResDto;
import com.example.car_booking.dto.UserResDto;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.User;

import java.util.List;

public interface ICarService {
    ResponseModel<CarResDto> addCar(CarDto car);
    ResponseModel<CarResDto> updateCar(Long id, CarDto car);
    ResponseModel<Void> deleteCar(Long id);
    ResponseModel<List<UserResDto>> getAllUsers();
}
