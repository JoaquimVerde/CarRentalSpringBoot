package SpringBootCarRental.CarRentalSpringBoot.repository;

import SpringBootCarRental.CarRentalSpringBoot.entity.Rental;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE rental AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
