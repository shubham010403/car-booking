package com.example.car_booking.service.serviceImpl;

import com.example.car_booking.dto.AuthRequestDto;
import com.example.car_booking.dto.UserDto;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.User;
import com.example.car_booking.repository.UserRepository;
import com.example.car_booking.service.IAuthService;
import com.example.car_booking.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public ResponseModel<String> authenticateAndGetToken(AuthRequestDto authRequestDto) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword())
        );

        // Check if authentication was successful
        if (authentication.isAuthenticated()) {
            // Generate JWT token for the authenticated user
            String token = jwtService.generateToken(authRequestDto.getUsername());
            return new ResponseModel<>("Token retrieved", HttpStatus.OK, token);
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @Override
    public ResponseModel<String> addUser(UserDto userDto){

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseModel<>("User created",HttpStatus.CREATED,null);
    }
}
