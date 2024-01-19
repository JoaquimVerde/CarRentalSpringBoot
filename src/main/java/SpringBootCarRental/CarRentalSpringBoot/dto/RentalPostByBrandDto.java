package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record RentalPostByBrandDto(
        @Valid
        String brand,
        @Valid
        @Email
        String email,
        @Future
        LocalDate dateOfRental,
        @Future
        LocalDate returnDate
) {
}
