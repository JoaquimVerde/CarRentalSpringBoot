package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.CarUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CarDto>> getCars() {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Car> addNewCar(@Valid @RequestBody CarDto car) {
        carService.addNewCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{carId}")
    public ResponseEntity<Car> deleteCar(@PathVariable("carId") Long carId) {
        carService.deleteCar(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping(path = "{carID}")
    public ResponseEntity<Car> updateCar(@PathVariable("carID") Long id, @Valid @RequestBody CarUpdateDto car) {
        carService.updateCar(id, car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{carId}")
    public ResponseEntity<CarDto> getCarDtoById(@PathVariable("carId") Long carId) {
        return new ResponseEntity<>(carService.getCarDtoById(carId), HttpStatus.OK);
    }


}
