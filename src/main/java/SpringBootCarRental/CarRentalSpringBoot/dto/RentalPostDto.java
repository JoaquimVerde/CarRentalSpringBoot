package SpringBootCarRental.CarRentalSpringBoot.dto;

import java.time.LocalDate;

public record RentalPostDto(

        Long clientID,

        Long carID,
        LocalDate returnDate

) {
}
