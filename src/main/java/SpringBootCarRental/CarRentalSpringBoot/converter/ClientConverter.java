package SpringBootCarRental.CarRentalSpringBoot.converter;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.entity.builders.ClientBuilder;

import java.time.LocalDate;
import java.util.List;

public class ClientConverter {

    public static ClientDto fromClientToClientDto(Client client){
        return new ClientDto(
                client.getName(),
                client.getEmail(),
                client.getDriverLicense(),
                client.getNif(),
                client.getDateOfBirth()
        );
    }
    public static List<ClientDto> ListClientToListClientDto(List<Client> clients){
        return clients.stream().map(ClientConverter::fromClientToClientDto).toList();
    }

    public static Client fromClientDtoToClient(ClientDto client){
        ClientBuilder builder = new ClientBuilder();
        builder.setName(client.name());
        builder.setEmail(client.email());
        builder.setDriverLicense(client.driverLicense());
        builder.setNif(client.nif());
        builder.setDateOfBirth(client.dateOfBirth());
        return builder.getResult();
    }
}
