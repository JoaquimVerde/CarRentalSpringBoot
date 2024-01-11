package SpringBootCarRental.CarRentalSpringBoot.repository;

import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
