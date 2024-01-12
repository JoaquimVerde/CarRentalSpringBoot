package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;

public record ClientUpdateDto(

        @Valid
        String name,
        @Valid
        String email

) {
}
