package com.example.car_booking.controller;

import com.example.car_booking.dto.AuthRequestDto;
import com.example.car_booking.dto.UserDto;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.User;
import com.example.car_booking.service.IAuthService;
import com.example.car_booking.service.ICarService;
import com.example.car_booking.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private ICarService carService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseModel<String> addNewUser(@RequestBody UserDto user){
        ResponseModel<String> responseModel;
        responseModel = authService.addUser(user);
        return responseModel;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseModel<String>> authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto) {
        ResponseModel<String> token = authService.authenticateAndGetToken(authRequestDto);
        return ResponseEntity.ok(token);
    }

}
