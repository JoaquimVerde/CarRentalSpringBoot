package SpringBootCarRental.CarRentalSpringBoot.converter;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.service.RentalService;

import java.time.LocalDate;
import java.util.List;

public class RentalConverter {

    public static RentalDto fromRentalToRentalDto(Rental rental){

        return new RentalDto(
                ClientConverter.fromClientToClientDto(rental.getClient()),
                CarConverter.fromCarToCarDto(rental.getCar()),
                rental.getDateOfRental(),
                rental.getReturnDate(),
                rental.getTotalPrice(),
                rental.isTerminated()
        );
    }

    public static List<RentalDto> ListRentalToListRentalDto(List<Rental> rentals){
        return rentals.stream().map(RentalConverter::fromRentalToRentalDto).toList();
    }


}
