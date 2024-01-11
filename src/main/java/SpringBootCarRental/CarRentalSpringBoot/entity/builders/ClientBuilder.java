package SpringBootCarRental.CarRentalSpringBoot.entity.builders;

import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class ClientBuilder {


    private String name;
    private String email;
    private int driverLicense;
    private int nif;
    private LocalDate dateOfBirth;




    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Client getResult(){
        return new Client (name, email, driverLicense, nif, dateOfBirth);

    }
}
