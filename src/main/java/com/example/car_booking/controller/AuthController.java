package com.example.car_booking.controller;

import com.example.car_booking.dto.AuthRequestDto;
import com.example.car_booking.entities.User;
import com.example.car_booking.service.ICarService;
import com.example.car_booking.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/register")
    public String addNewUser(@RequestBody User user){
        return carService.addUser(user);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),authRequestDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequestDto.getUsername());
        }
        else {
            throw new UsernameNotFoundException("Invalid user request");
        }

    }

}
