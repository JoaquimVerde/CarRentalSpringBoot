package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;


public record CarUpdateDto(
        @Valid
        int km

) {
}
