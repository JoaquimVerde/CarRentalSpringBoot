package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;

import java.util.List;

public interface RentalServiceInterface {
    List<RentalDto> getRentals();

    void addNewRental(RentalPostDto rental);

    void deleteRental(Long rentalId);
}
