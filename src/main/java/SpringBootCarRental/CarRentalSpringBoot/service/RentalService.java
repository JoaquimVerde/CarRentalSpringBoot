package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.RentalConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.IDException;
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
    public void addNewRental(RentalPostDto rental) {

        Car car = carService.getById(rental.carID());
        Client client = clientService.getById(rental.clientID());
        Rental newRental = RentalConverter.fromRentalPostDtotoRental(client, car, rental.returnDate());

        //Rental newRental = new Rental(client, car, rental.returnDate());

        rentalRepository.save(newRental);
    }

    @Override
    public void deleteRental(Long rentalId) {
        boolean exists = rentalRepository.existsById(rentalId);
        if (!exists) {
            throw new IDException(Messages.RENTAL_ID_NOT_FOUND + rentalId);
        }
        rentalRepository.deleteById(rentalId);
    }
    @Override
    public void updateRental(Long id, RentalUpdateDto rental) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        if (rentalOptional.isEmpty()) {
            throw new IDException(Messages.RENTAL_ID_NOT_FOUND + id);
        }

        Rental rentalToUpdate = rentalOptional.get();

        if(rental.returnDate() != null && rental.returnDate().isAfter(LocalDate.now()) && !rental.returnDate().equals(rentalToUpdate.getReturnDate())){
            rentalToUpdate.setReturnDate(rental.returnDate());
        }

        rentalRepository.save(rentalToUpdate);
    }

}
