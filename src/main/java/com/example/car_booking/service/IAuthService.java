package com.example.car_booking.service;

import com.example.car_booking.dto.AuthRequestDto;
import com.example.car_booking.dto.UserDto;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.User;

public interface IAuthService {
    ResponseModel<String> addUser(UserDto user);
    ResponseModel<String> authenticateAndGetToken(AuthRequestDto authRequestDto);
}
