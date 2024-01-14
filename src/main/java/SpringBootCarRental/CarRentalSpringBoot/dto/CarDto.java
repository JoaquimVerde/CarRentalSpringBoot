package SpringBootCarRental.CarRentalSpringBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CarDto(
        @Valid
        @NotBlank(message = "Brand is mandatory!")
        String brand,
        @Valid
        @NotBlank(message = "License Plate is mandatory!")
        String licensePlate,
        @Valid
        int horsePower,
        @Valid
        int km,
        @Valid
        LocalDate acquisitionDate,
        @Valid
        double dailyRate
) {
}
