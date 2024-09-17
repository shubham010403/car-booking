package com.example.car_booking.service.serviceImpl;

import com.example.car_booking.entities.ResponseModel;
import com.example.car_booking.entities.Booking;
import com.example.car_booking.entities.Car;
import com.example.car_booking.entities.User;
import com.example.car_booking.repository.BookingRepository;
import com.example.car_booking.repository.CarRepository;
import com.example.car_booking.repository.UserRepository;
import com.example.car_booking.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseModel<Car> addCar(Car car) {
        Car savedCar = carRepository.save(car);
        return new ResponseModel<>("Car added successfully", HttpStatus.CREATED, savedCar);
    }

    @Override
    public ResponseModel<Car> updateCar(Long id, Car car) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car existingCar = carOptional.get();
            existingCar.setModel(car.getModel());
            existingCar.setBrand(car.getBrand());
            existingCar.setAvailability(car.isAvailability());
            existingCar.setRentalPrice(car.getRentalPrice());
            Car updatedCar = carRepository.save(existingCar);
            return new ResponseModel<>("Car updated successfully", HttpStatus.OK, updatedCar);
        } else {
            return new ResponseModel<>("Car not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseModel<Void> deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return new ResponseModel<>("Car deleted successfully", HttpStatus.NO_CONTENT, null);
        } else {
            return new ResponseModel<>("Car not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseModel<List<Car>> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return new ResponseModel<>("List of cars retrieved", HttpStatus.OK, cars);
    }
    @Override
    public ResponseModel<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseModel<>("List of users retrieved", HttpStatus.OK, users);
    }

    @Override
    public ResponseModel<List<Car>> getAvailableCars() {
        List<Car> availableCars = carRepository.findByAvailabilityTrue();
        return new ResponseModel<>("Available cars retrieved", HttpStatus.OK, availableCars);
    }

    @Override
    public ResponseModel<Booking> bookCar(Long carId, Long userId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (carOptional.isPresent() && userOptional.isPresent()) {
            Car car = carOptional.get();
            User user = userOptional.get();

            if (car.isAvailability()) {
                // Create a new booking
                Booking booking = new Booking();
                booking.setCar(car);
                booking.setUser(user);
                booking.setBookingDate(LocalDate.now());
                // Assuming return date is set manually or calculated elsewhere
                booking.setReturnDate(LocalDate.now().plusDays(7)); // Example return date
                bookingRepository.save(booking);

                // Update car availability
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
    @Override
    public String addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User created";
    }

}
