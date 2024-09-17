package com.example.car_booking.service;

import com.example.car_booking.dto.AuthRequestDto;
import com.example.car_booking.entities.User;

public interface IAuthService {
    String addUser(User user);
    String authenticateAndGetToken(AuthRequestDto authRequestDto);
}
