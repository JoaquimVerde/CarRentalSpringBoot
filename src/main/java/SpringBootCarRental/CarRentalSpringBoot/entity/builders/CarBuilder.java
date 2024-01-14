package SpringBootCarRental.CarRentalSpringBoot.entity.builders;

import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class CarBuilder {

    private String brand;
    private String licensePlate;
    private int horsePower;
    private int km;
    private LocalDate acquisitionDate;
    private double dailyRate;



    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Car getResult(){
        return new Car(brand, licensePlate, horsePower, km, dailyRate);
    }
}
