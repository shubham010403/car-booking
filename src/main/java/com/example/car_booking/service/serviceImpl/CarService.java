package com.example.car_booking.service.serviceImpl;

import com.example.car_booking.dto.CarDto;
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

    @Override
    public ResponseModel<Car> addCar(CarDto carDto) {
        // Convert CarDto to Car entity
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setBrand(carDto.getBrand());
        car.setAvailability(carDto.isAvailability());
        car.setRentalPrice(carDto.getRentalPrice());

        // Save the Car entity
        Car savedCar = carRepository.save(car);

        return new ResponseModel<>("Car added successfully", HttpStatus.CREATED, savedCar);
    }


    @Override
    public ResponseModel<Car> updateCar(Long id, CarDto carDto) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car existingCar = carOptional.get();

            // Update the car entity with values from CarDto
            existingCar.setModel(carDto.getModel());
            existingCar.setBrand(carDto.getBrand());
            existingCar.setAvailability(carDto.isAvailability());
            existingCar.setRentalPrice(carDto.getRentalPrice());

            // Save the updated car entity
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
    public ResponseModel<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseModel<>("List of users retrieved", HttpStatus.OK, users);
    }

}
