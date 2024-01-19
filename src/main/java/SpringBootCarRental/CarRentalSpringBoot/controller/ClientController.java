package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientUpdateDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Car;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> getClients() {
        return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<ClientDto> addNewClient(@Valid @RequestBody ClientDto client) {

        return new ResponseEntity<>(clientService.addNewClient(client), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{clientId}")
    public ResponseEntity<Client> deleteClient(@PathVariable("clientId") Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "{clientID}")
    public ResponseEntity<Car> updateClient(@PathVariable("clientID") Long id, @Valid @RequestBody ClientUpdateDto client) {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{clientId}")
    public ResponseEntity<ClientDto> getClientDtoById(@PathVariable("clientId") Long clientId) {
        return new ResponseEntity<>(clientService.getClientDtoById(clientId), HttpStatus.OK);
    }


}
