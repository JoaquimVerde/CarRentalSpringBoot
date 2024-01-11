package SpringBootCarRental.CarRentalSpringBoot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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






    public Car() {
    }

    public Car(String brand, String licensePlate, int horsePower, int km) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.horsePower = horsePower;
        this.km = km;
        this.acquisitionDate = LocalDate.now();

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


}
