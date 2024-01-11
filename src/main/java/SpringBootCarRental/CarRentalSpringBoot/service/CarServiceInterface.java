package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;

import java.util.List;

public interface CarServiceInterface {
    List<CarDto> getCars();

    void addNewCar(CarDto car);

    void deleteCar(Long carId);
}
