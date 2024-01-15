package SpringBootCarRental.CarRentalSpringBoot.converter;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.builders.CarBuilder;

import java.time.LocalDate;
import java.util.List;

public class CarConverter {

    public static CarDto fromCarToCarDto(Car car){
        return new CarDto(
                car.getBrand(),
                car.getLicensePlate(),
                car.getHorsePower(),
                car.getKm(),
                car.getAcquisitionDate(),
                car.getDailyRate(),
                car.getAvailability()
        );
    }
    public static List<CarDto> ListCarToListCarDto(List<Car> cars){
        return cars.stream().map(CarConverter::fromCarToCarDto).toList();
    }

    public static Car fromCarDtoToCar(CarDto car){
        CarBuilder builder = new CarBuilder();
        builder.setBrand(car.brand());
        builder.setLicensePlate(car.licensePlate());
        builder.setHorsePower(car.horsePower());
        builder.setKm(car.km());
        builder.setDailyRate(car.dailyRate());
        return builder.getResult();

    }
}
