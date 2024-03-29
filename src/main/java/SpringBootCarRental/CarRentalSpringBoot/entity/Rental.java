package SpringBootCarRental.CarRentalSpringBoot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Car car;

    private LocalDate dateOfRental;
    private LocalDate returnDate;
    private double totalPrice;
    private boolean isTerminated;


    public Rental() {
    }

    public Rental(Client client, Car car, LocalDate returnDate, LocalDate dateOfRental) {
        this.client = client;
        this.car = car;
        this.dateOfRental = dateOfRental;
        this.returnDate = returnDate;
        setTotalPrice();
        car.setAvailable(false);
        car.setHasARegisteredRental(true);
        client.setHasARegisteredRental(true);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(LocalDate dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        totalPrice = dateOfRental.until(getReturnDate(), ChronoUnit.DAYS) * car.getDailyRate();
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }
}
