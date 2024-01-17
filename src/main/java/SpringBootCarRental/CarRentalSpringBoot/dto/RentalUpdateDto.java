package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record RentalUpdateDto(

        @Valid
        @Future(message = "return date cannot be in the past.")
        LocalDate returnDate,
        @Valid
        boolean isTerminated
) {
}
