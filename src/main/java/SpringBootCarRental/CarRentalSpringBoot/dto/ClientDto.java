package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClientDto(

        @Valid
        @NotBlank(message = "Name is mandatory!")
        String name,
        @Valid
        @NotBlank(message = "Email is mandatory!")
        String email,
        @Valid
        int driverLicense,
        @Valid
        int nif,
        @Valid
        LocalDate dateOfBirth

) {
}
