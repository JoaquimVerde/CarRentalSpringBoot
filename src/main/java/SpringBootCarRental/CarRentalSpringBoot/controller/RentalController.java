package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RentalDto>> getRentals() {
        return new ResponseEntity<>(rentalService.getRentals(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Rental> addNewRental(@Valid @RequestBody RentalPostDto rental) {
        rentalService.addNewRental(rental);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{rentalId}")
    public ResponseEntity<Rental> deleteRental(@PathVariable("rentalId") Long rentalId) {
        rentalService.deleteRental(rentalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
