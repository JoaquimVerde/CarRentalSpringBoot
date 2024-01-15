package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record ClientUpdateDto(

        @Valid
        String name,
        @Valid
        @Email(message = "Email not valid.")
        String email

) {
}
