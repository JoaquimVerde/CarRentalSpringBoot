package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientUpdateDto;

import java.util.List;

public interface ClientServiceInterface {
    List<ClientDto> getClients();

    void addNewClient(ClientDto client);

    void deleteClient(Long clientId);

    void updateClient(Long id, ClientUpdateDto client);
}
