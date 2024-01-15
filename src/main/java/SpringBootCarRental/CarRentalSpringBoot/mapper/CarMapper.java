package SpringBootCarRental.CarRentalSpringBoot.mapper;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper

public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto fromCarToCarDto(Car car);
    List<CarDto> ListCarToListCarDto(List<Car> cars);
    Car fromCarDtoToCar(CarDto car);

}
