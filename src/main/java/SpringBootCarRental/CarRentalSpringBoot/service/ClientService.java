package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.ClientConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceInterface {

    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> getClients() {
        List<Client> clients = clientRepository.findAll();
        return ClientConverter.ListClientToListClientDto(clients);
    }

    @Override
    public void addNewClient(ClientDto client) {
        Optional<Client> clientOptional = this.clientRepository.findClientByEmail(client.email());
        if (clientOptional.isPresent())
            throw new IllegalStateException("client already exists");
        Client newClient = ClientConverter.fromClientDtoToClient(client);
        clientRepository.save(newClient);
    }

    @Override
    public void deleteClient(Long clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + clientId + " does not exist");
        }
        clientRepository.deleteById(clientId);
    }

    @Override
    public void updateClient(Long id, ClientUpdateDto client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new IllegalStateException("Car with id " + id + " does not exist");
        }

        Client clientToUpdate = clientOptional.get();

        if(client.name() != null && client.name().length() > 0 && !client.name().equals(clientToUpdate.getName())){
            clientToUpdate.setName(client.name());
        }

        if(client.email() != null && client.email().length() > 0 && !client.email().equals(clientToUpdate.getEmail())){
            clientToUpdate.setEmail(client.email());
        }

        clientRepository.save(clientToUpdate);
    }
}
