package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;

import java.util.List;

public interface CarServiceInterface {
    List<CarDto> getCars();

    CarDto addNewCar(CarDto car);

    void deleteCar(Long carId);

    void updateCar(Long id, CarUpdateDto car);

    Car getById(Long id);

    CarDto getCarDtoById(Long id);
}
