package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.CarConverter;
import SpringBootCarRental.CarRentalSpringBoot.converter.ClientConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;

import SpringBootCarRental.CarRentalSpringBoot.entity.builders.CarBuilder;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.CarPlatesAlreadyExistException;
import SpringBootCarRental.CarRentalSpringBoot.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CarServiceTest {

    @MockBean
    private CarRepository carRepositoryMock;

    private CarService carService;

    static MockedStatic<CarConverter> mockedCarConverter = Mockito.mockStatic(CarConverter.class);

    @BeforeEach
    public void setUp() {
        carService = new CarService(carRepositoryMock);
    }

    @Test
    void testCreateCarCallsRepositoryAndConverter() {
        //given
        CarDto carDto = new CarDto("Mercedes", "11-AA-22", 1300, 55000, LocalDate.now(), 10, true);
        Car car = new Car("Mercedes", "11-AA-22", 1300, 55000, 10);
        when(carRepositoryMock.findCarByLicensePlate(carDto.licensePlate())).thenReturn(Optional.empty());
        mockedCarConverter.when(() -> CarConverter.fromCarDtoToCar(carDto)).thenReturn(car);
        when(carRepositoryMock.save(Mockito.any())).thenReturn(car);

        //when
        carService.addNewCar(carDto);


        //then
        verify(carRepositoryMock, times(1)).findCarByLicensePlate(carDto.licensePlate());

        mockedCarConverter.verify(() -> CarConverter.fromCarDtoToCar(carDto));
        mockedCarConverter.verifyNoMoreInteractions();

        verify(carRepositoryMock, times(1)).save(car);
        verifyNoMoreInteractions(carRepositoryMock);
        assertEquals(car, carService.addNewCar(carDto));
    }

    @Test
    void createCarWithDuplicatedEmailThrowsException() {
        //given
        CarDto carDto = new CarDto("Mercedes", "11-AA-22", 1300, 55000, LocalDate.now(), 10, true);

        //when
        when(carRepositoryMock.findCarByLicensePlate(carDto.licensePlate())).thenReturn(Optional.of(new Car()));
        //then
        assertThrows(CarPlatesAlreadyExistException.class, () -> carService.addNewCar(carDto));
        assertEquals("Car license plate already exists", assertThrows(CarPlatesAlreadyExistException.class, () -> carService.addNewCar(carDto)).getMessage());

    }

    @Test
    void testDeleteCarCallsRepository(){
        //given
        Car car = Car.builder().id(1L).brand("mercedes").build();
        when(carRepositoryMock.existsById(car.getId())).thenReturn(true);
        when(carRepositoryMock.findById(car.getId())).thenReturn(Optional.of(car));
        //when
        carService.deleteCar(car.getId());
        //then
        verify(carRepositoryMock, times(1)).deleteById(car.getId());
    }

}