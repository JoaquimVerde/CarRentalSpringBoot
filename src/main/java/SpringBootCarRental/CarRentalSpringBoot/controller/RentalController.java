package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalUpdateDto;
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
    public ResponseEntity<RentalPostDto> addNewRental(@Valid @RequestBody RentalPostDto rental) {

        return new ResponseEntity<>(rentalService.addNewRental(rental), HttpStatus.CREATED);
    }

    /*@PostMapping("/")
    public ResponseEntity<RentalPostByBrandDto> addRentalByBrand(@Valid @RequestBody RentalPostByBrandDto rental) {

        return new ResponseEntity<>(rentalService.addRentalByBrand(rental), HttpStatus.CREATED);
    }*/

    @DeleteMapping(path = "{rentalId}")
    public ResponseEntity<Rental> deleteRental(@PathVariable("rentalId") Long rentalId) {
        rentalService.deleteRental(rentalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "{rentalID}")
    public ResponseEntity<Rental> updateRental(@PathVariable("rentalID") Long id, @Valid @RequestBody RentalUpdateDto rental) {
        rentalService.updateRental(id, rental);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{rentalId}")
    public ResponseEntity<RentalDto> getRentalDtoById(@PathVariable("rentalId") Long rentalId) {
        return new ResponseEntity<>(rentalService.getRentalDtoById(rentalId), HttpStatus.OK);
    }

}
