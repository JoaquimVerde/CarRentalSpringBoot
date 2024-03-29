package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.CarConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.*;
import SpringBootCarRental.CarRentalSpringBoot.repository.CarRepository;
import SpringBootCarRental.CarRentalSpringBoot.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {


    private CarRepository carRepository;


    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> getCars() {
        List<Car> cars = carRepository.findAll();
        return CarConverter.ListCarToListCarDto(cars);

    }

    @Override
    public CarDto addNewCar(CarDto car) {
        Optional<Car> carOptional = this.carRepository.findCarByLicensePlate(car.licensePlate());
        if (carOptional.isPresent())
            throw new CarPlatesAlreadyExistException(Messages.CAR_PLATES_ALREADY_EXIST.getMessage());
        Car newCar = CarConverter.fromCarDtoToCar(car);
        carRepository.save(newCar);
        return car;
    }

    @Override
    public void deleteCar(Long carId) {

        Car car = carRepository.findById(carId).orElseThrow(
                () -> new CarIdNotFoundException(Messages.CAR_ID_NOT_FOUND.getMessage() + carId));

        if (car.hasARegisteredRental()) {
            throw new CannotDeleteException(Messages.CANNOT_DELETE_CAR.getMessage() + carId);
        }
        carRepository.deleteById(carId);
    }

    @Override
    public void updateCar(Long id, CarUpdateDto car) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            throw new CarIdNotFoundException(Messages.CAR_ID_NOT_FOUND.getMessage() + id);
        }

        Car carToUpdate = carOptional.get();
        if (car.km() < carToUpdate.getKm()) {
            throw new CannotDecreaseKmException(Messages.CANNOT_DECREASE_KM.getMessage());
        }

        carToUpdate.setKm(car.km());

        carRepository.save(carToUpdate);
    }

    @Override
    public Car getById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new CarIdNotFoundException(Messages.CAR_ID_NOT_FOUND.getMessage() + id);
        }
        return optionalCar.get();
    }

    @Override
    public CarDto getCarDtoById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new CarIdNotFoundException(Messages.CAR_ID_NOT_FOUND.getMessage() + id);
        }
        return CarConverter.fromCarToCarDto(optionalCar.get());
    }

    public Car getCarByBrand(String brand) {
        Car car = carRepository.getCarByBrand(brand).orElseThrow(() -> new AppExceptions(Messages.CANNOT_FIND_THAT_BRAND_CAR.getMessage()));
        if (!car.isAvailable()) {
            throw new CarUnavailableException(Messages.UNAVAILABLE_TO_RENT.getMessage());
        }
        return car;
    }


}
