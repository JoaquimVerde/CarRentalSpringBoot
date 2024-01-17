package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.RentalConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.AppExceptions;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.CarUnavailableException;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.RentalIdNotFoundException;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.ReturnDateInThePastException;
import SpringBootCarRental.CarRentalSpringBoot.repository.RentalRepository;
import SpringBootCarRental.CarRentalSpringBoot.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService implements RentalServiceInterface {

    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final ClientService clientService;


    @Autowired
    public RentalService(RentalRepository rentalRepository, CarService carService, ClientService clientService) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.clientService = clientService;
    }

    @Override
    public List<RentalDto> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return RentalConverter.ListRentalToListRentalDto(rentals);
    }

    @Override
    public Rental addNewRental(RentalPostDto rental) {

        Car car = carService.getById(rental.carID());
        if(!car.isAvailable()){
            throw new CarUnavailableException(Messages.UNAVAILABLE_TO_RENT.getMessage());
        }

        Client client = clientService.getById(rental.clientID());

        if(rental.returnDate().isBefore(LocalDate.now())){
            throw new ReturnDateInThePastException(Messages.RETURN_DATE_CANNOT_BE_PAST.getMessage());
        }

        Rental newRental = new Rental(client, car, rental.returnDate());

        return rentalRepository.save(newRental);
    }

    @Override
    public void deleteRental(Long rentalId) {

        if (!rentalRepository.existsById(rentalId)) {
            throw new RentalIdNotFoundException(Messages.RENTAL_ID_NOT_FOUND.getMessage() + rentalId);
        }

        rentalRepository.deleteById(rentalId);
    }

    @Override
    public void updateRental(Long id, RentalUpdateDto rental) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        if (rentalOptional.isEmpty()) {
            throw new RentalIdNotFoundException(Messages.RENTAL_ID_NOT_FOUND.getMessage() + id);
        }

        Rental rentalToUpdate = rentalOptional.get();

        if(rental.returnDate() != null && rental.returnDate().isAfter(LocalDate.now()) && !rental.returnDate().equals(rentalToUpdate.getReturnDate())){
            rentalToUpdate.setReturnDate(rental.returnDate());
            rentalToUpdate.setTotalPrice();
        }
        rentalToUpdate.setTerminated(rental.isTerminated());
        if(rentalToUpdate.isTerminated()){
            rentalToUpdate.getCar().setAvailable(true);
        }

        rentalRepository.save(rentalToUpdate);
    }

    @Override
    public RentalDto getRentalDtoById(Long id){
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            throw new RentalIdNotFoundException(Messages.RENTAL_ID_NOT_FOUND.getMessage() + id);
        }
        return RentalConverter.fromRentalToRentalDto(optionalRental.get());
    }

}
