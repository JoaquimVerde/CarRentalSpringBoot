package SpringBootCarRental.CarRentalSpringBoot.dto;

import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;

import java.time.LocalDate;

public record RentalDto(

        Client client,

        Car car,

        LocalDate dateOfRental,

        LocalDate returnDate

) {
}
