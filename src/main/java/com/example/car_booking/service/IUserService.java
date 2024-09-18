package com.example.car_booking.service;

import com.example.car_booking.dto.BookingResDto;
import com.example.car_booking.dto.CarResDto;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.ResponseModel;

import java.util.List;

public interface IUserService {
    ResponseModel<List<CarResDto>> getAvailableCars();
    ResponseModel<BookingResDto> bookCar(Long carId);
    ResponseModel<List<BookingResDto>> getUserBookings(Long userId);

}
