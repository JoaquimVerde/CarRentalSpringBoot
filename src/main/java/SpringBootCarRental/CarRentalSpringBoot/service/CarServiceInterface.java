package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarUpdateDto;

import java.util.List;

public interface CarServiceInterface {
    List<CarDto> getCars();

    void addNewCar(CarDto car);

    void deleteCar(Long carId);

    void updateCar(Long id, CarUpdateDto car);
}
