package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.RentalConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.AppExceptions;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.RentalIdNotFoundException;
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
        if(car.getAvailability().equals("Unavailable")){
            throw new AppExceptions(Messages.UNAVAILABLE_TO_RENT.getMessage());
        }
        Client client = clientService.getById(rental.clientID());

        Rental newRental = new Rental(client, car, rental.returnDate());

        rentalRepository.save(newRental);
    }

    @Override
    public void deleteRental(Long rentalId) {
        boolean exists = rentalRepository.existsById(rentalId);
        if (!exists) {
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

        rentalRepository.save(rentalToUpdate);
    }

}
