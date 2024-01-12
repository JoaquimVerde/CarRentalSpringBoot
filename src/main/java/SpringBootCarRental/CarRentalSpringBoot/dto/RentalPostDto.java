package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;

import java.time.LocalDate;

public record RentalPostDto(
        @Valid
        Long clientID,
        @Valid
        Long carID,
        @Valid
        LocalDate returnDate

) {
}
