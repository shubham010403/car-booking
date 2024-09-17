package com.example.car_booking.service.serviceImpl;

import com.example.car_booking.dto.BookingDto;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.User;
import com.example.car_booking.repository.BookingRepository;
import com.example.car_booking.repository.CarRepository;
import com.example.car_booking.repository.UserRepository;
import com.example.car_booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseModel<List<Car>> getAvailableCars() {
        List<Car> availableCars = carRepository.findByAvailabilityTrue();
        return new ResponseModel<>("Available cars retrieved", HttpStatus.OK, availableCars);
    }

    @Override
    public ResponseModel<Booking> bookCar(Long carId) {
        Optional<Car> carOptional = carRepository.findById(carId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Optional<User> userOptional = userRepository.findByName(name);

        if (carOptional.isPresent() && userOptional.isPresent()) {
            Car car = carOptional.get();
            User user = userOptional.get();

            if (car.isAvailability()) {
                Booking booking = new Booking();
                booking.setCar(car);
                booking.setUser(user);
                booking.setBookingDate(LocalDate.now());
                booking.setReturnDate(LocalDate.now().plusDays(7));

                bookingRepository.save(booking);

                car.setAvailability(false);
                carRepository.save(car);

                return new ResponseModel<>("Car booked successfully", HttpStatus.CREATED, booking);
            } else {
                return new ResponseModel<>("Car is not available", HttpStatus.BAD_REQUEST, null);
            }
        } else {
            return new ResponseModel<>("Car or User not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseModel<List<Booking>> getUserBookings(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Booking> bookings = bookingRepository.findByUserId(userId);
            return new ResponseModel<>("User bookings retrieved", HttpStatus.OK, bookings);
        } else {
            return new ResponseModel<>("User not found", HttpStatus.NOT_FOUND, null);
        }
    }
}
