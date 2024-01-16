package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.ClientConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.CannotDeleteException;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.ClientIdNotFoundException;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.EmailAlreadyExistsException;
import SpringBootCarRental.CarRentalSpringBoot.repository.ClientRepository;
import SpringBootCarRental.CarRentalSpringBoot.util.Messages;
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
    public Client addNewClient(ClientDto client) {
        Optional<Client> clientOptional = this.clientRepository.findClientByEmail(client.email());
        if (clientOptional.isPresent())
            throw new EmailAlreadyExistsException(Messages.CLIENT_EMAIL_ALREADY_EXISTS.getMessage());
        Client newClient = ClientConverter.fromClientDtoToClient(client);
        return clientRepository.save(newClient);
    }

    @Override
    public void deleteClient(Long clientId) {

        if (!clientRepository.existsById(clientId)) {
            throw new ClientIdNotFoundException(Messages.CLIENT_ID_NOT_FOUND.getMessage() + clientId);
        }
        if(clientRepository.getById(clientId).HasARegisteredRental()){
            throw new CannotDeleteException(Messages.CANNOT_DELETE_CLIENT.getMessage() + clientId);
        }
        clientRepository.deleteById(clientId);
    }

    @Override
    public void updateClient(Long id, ClientUpdateDto client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new ClientIdNotFoundException(Messages.CLIENT_ID_NOT_FOUND.getMessage() + id);
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
    @Override
    public Client getById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new ClientIdNotFoundException(Messages.CLIENT_ID_NOT_FOUND.getMessage() + id);
        }
        return optionalClient.get();
    }

    @Override
    public ClientDto getClientDtoById(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new ClientIdNotFoundException(Messages.CLIENT_ID_NOT_FOUND.getMessage() + id);
        }
        return ClientConverter.fromClientToClientDto(optionalClient.get());
    }


}
