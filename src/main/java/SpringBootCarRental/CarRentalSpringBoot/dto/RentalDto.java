package SpringBootCarRental.CarRentalSpringBoot.dto;

import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import jakarta.validation.Valid;

import java.time.LocalDate;

public record RentalDto(

        @Valid
        ClientDto client,
        @Valid
        CarDto car,
        @Valid
        LocalDate dateOfRental,
        @Valid
        LocalDate returnDate,
        @Valid
        double totalPrice

) {
}
