package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.CarConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.IDException;
import SpringBootCarRental.CarRentalSpringBoot.repository.CarRepository;
import SpringBootCarRental.CarRentalSpringBoot.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;

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
    public void addNewCar(CarDto car) {
        Optional<Car> carOptional = this.carRepository.findCarByLicensePlate(car.licensePlate());
        if (carOptional.isPresent())
            throw new IDException(Messages.CAR_PLATES_ALREADY_EXIST);
        Car newCar = CarConverter.fromCarDtoToCar(car);
        carRepository.save(newCar);
    }

    @Override
    public void deleteCar(Long carId) {
        boolean exists = carRepository.existsById(carId);
        if (!exists) {
            throw new IDException(Messages.CAR_ID_NOT_FOUND + carId);
        }
        carRepository.deleteById(carId);
    }

    @Override
    public void updateCar(Long id, CarUpdateDto car) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            throw new IDException(Messages.CAR_ID_NOT_FOUND + id);
        }

        Car carToUpdate = carOptional.get();
        if (car.km() < carToUpdate.getKm()) {
            throw new IDException(Messages.CANNOT_DECREASE_KM);
        }

        carToUpdate.setKm(car.km());

        carRepository.save(carToUpdate);
    }

    @Override
    public Car getById(Long id){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new IDException(Messages.CAR_ID_NOT_FOUND + id);
        }
        return optionalCar.get();
    }


}
