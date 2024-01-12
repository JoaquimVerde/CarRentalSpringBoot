package SpringBootCarRental.CarRentalSpringBoot.dto;

import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import jakarta.validation.Valid;

import java.time.LocalDate;

public record RentalDto(

        @Valid
        Client client,
        @Valid
        Car car,
        @Valid
        LocalDate dateOfRental,
        @Valid
        LocalDate returnDate

) {
}
