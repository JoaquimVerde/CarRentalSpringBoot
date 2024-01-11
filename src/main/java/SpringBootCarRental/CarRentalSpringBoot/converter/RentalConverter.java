package SpringBootCarRental.CarRentalSpringBoot.converter;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.service.RentalService;

import java.util.List;

public class RentalConverter {

    public static RentalDto fromRentalToRentalDto(Rental rental){
        return new RentalDto(
                rental.getClient(),
                rental.getCar(),
                rental.getDateOfRental()
        );
    }

    public static List<RentalDto> ListRentalToListRentalDto(List<Rental> rentals){
        return rentals.stream().map(RentalConverter::fromRentalToRentalDto).toList();
    }

    public static Rental fromRentalPostDtotoRental (Client client, Car car){
        return new Rental(client, car);
    }


}
