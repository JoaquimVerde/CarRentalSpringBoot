package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;

import java.time.LocalDate;

public record RentalUpdateDto(

        @Valid
        LocalDate returnDate,
        @Valid
        boolean isTerminated
) {
}
