package SpringBootCarRental.CarRentalSpringBoot.repository;

import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByLicensePlate(String licensePlate);

    //@Query(value = "SELECT * FROM car WHERE brand = brand AND is_available = 1")
    Optional<Car> getCarByBrand(String brand);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE car AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
