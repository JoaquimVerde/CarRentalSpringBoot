package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.AppExceptions;

import java.util.List;

public interface RentalServiceInterface {
    List<RentalDto> getRentals();

    Rental addNewRental(RentalPostDto rental) throws AppExceptions;

    void deleteRental(Long rentalId);

    void updateRental(Long id, RentalUpdateDto rental);

    RentalDto getRentalDtoById(Long id);
}
