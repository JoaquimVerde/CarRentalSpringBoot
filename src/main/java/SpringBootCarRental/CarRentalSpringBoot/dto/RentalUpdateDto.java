package SpringBootCarRental.CarRentalSpringBoot.dto;

import java.time.LocalDate;

public record RentalUpdateDto(

        LocalDate returnDate
) {
}
