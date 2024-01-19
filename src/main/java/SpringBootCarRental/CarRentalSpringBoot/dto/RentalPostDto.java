package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record RentalPostDto(
        @Valid
        Long clientID,
        @Valid
        Long carID,
        @Valid
        @Future(message = "return date cannot be in the past.")
        LocalDate returnDate,
        @Valid
        @Future
        LocalDate dateOfRental

) {
}
