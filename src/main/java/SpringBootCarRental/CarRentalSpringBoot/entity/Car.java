package SpringBootCarRental.CarRentalSpringBoot.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
@Builder
@Entity
@Table
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    @Column(unique = true)
    private String licensePlate;
    private int horsePower;
    private int km;
    private LocalDate acquisitionDate;

    private double dailyRate;
    private boolean isAvailable;
    private boolean hasARegisteredRental;






    public Car() {
    }

    public Car(String brand, String licensePlate, int horsePower, int km, double dailyRate) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.horsePower = horsePower;
        this.km = km;
        this.dailyRate = dailyRate;
        this.acquisitionDate = LocalDate.now();
        setAvailable(true);

    }

    public Car(Long id, String brand, String licensePlate, int horsePower, int km, LocalDate acquisitionDate, double dailyRate, boolean isAvailable, boolean hasARegisteredRental) {
        this.id = id;
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.horsePower = horsePower;
        this.km = km;
        this.acquisitionDate = acquisitionDate;
        this.dailyRate = dailyRate;
        this.isAvailable = isAvailable;
        this.hasARegisteredRental = hasARegisteredRental;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public boolean hasARegisteredRental() {
        return hasARegisteredRental;
    }

    public void setHasARegisteredRental(boolean hasARegisteredRental) {
        this.hasARegisteredRental = hasARegisteredRental;
    }
}
