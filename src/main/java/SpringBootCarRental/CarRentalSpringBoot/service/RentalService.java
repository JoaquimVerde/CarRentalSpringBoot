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
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public RentalService(RentalRepository rentalRepository, CarRepository carRepository, ClientRepository clientRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<RentalDto> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return RentalConverter.ListRentalToListRentalDto(rentals);
    }

    @Override
    public void addNewRental(RentalPostDto rental) {
        Optional<Car> carOptional = this.carRepository.findById(rental.carID());
        if (carOptional.isEmpty())
            throw new IllegalStateException("car ID does not exist");
        Optional<Client> clientOptional = this.clientRepository.findById(rental.clientID());
        if (clientOptional.isEmpty())
            throw new IllegalStateException("client ID does not exist");
        Rental newRental = RentalConverter.fromRentalPostDtotoRental(clientOptional.get(),carOptional.get());
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
