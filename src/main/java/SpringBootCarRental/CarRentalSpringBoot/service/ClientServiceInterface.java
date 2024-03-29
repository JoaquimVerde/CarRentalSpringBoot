package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.AppExceptions;

import java.util.List;

public interface ClientServiceInterface {
    List<ClientDto> getClients();

    ClientDto addNewClient(ClientDto client);

    void deleteClient(Long clientId);

    void updateClient(Long id, ClientUpdateDto client);

    Client getById(Long id) throws AppExceptions;

    ClientDto getClientDtoById(Long id);
}
