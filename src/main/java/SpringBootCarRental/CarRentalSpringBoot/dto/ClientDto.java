package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record ClientDto(

        @Valid
        @NotBlank(message = "Name is mandatory!")
        String name,
        @Valid
        @NotBlank(message = "Email is mandatory!")
        @Email(message = "Email not valid!")
        String email,
        @Valid
        int driverLicense,
        @Valid
        int nif,
        @Valid
        @Past(message = "Date of birth must be in the past.")
        LocalDate dateOfBirth

) {
}
