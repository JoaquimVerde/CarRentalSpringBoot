package SpringBootCarRental.CarRentalSpringBoot.repository;

import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE client AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
