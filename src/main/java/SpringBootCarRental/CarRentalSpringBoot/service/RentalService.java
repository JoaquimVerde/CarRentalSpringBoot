package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.CarConverter;
import SpringBootCarRental.CarRentalSpringBoot.converter.RentalConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import SpringBootCarRental.CarRentalSpringBoot.repository.CarRepository;
import SpringBootCarRental.CarRentalSpringBoot.repository.ClientRepository;
import SpringBootCarRental.CarRentalSpringBoot.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Rental newRental = RentalConverter.fromRentalPostDtotoRental(client,car);
        //Rental newRental = new Rental(clientOptional.get(), carOptional.get());
        rentalRepository.save(newRental);
    }

    @Override
    public void deleteRental(Long rentalId) {
        boolean exists = rentalRepository.existsById(rentalId);
        if (!exists) {
            throw new IllegalStateException("Rental with id " + rentalId + " does not exist");
        }
        rentalRepository.deleteById(rentalId);
    }
}
