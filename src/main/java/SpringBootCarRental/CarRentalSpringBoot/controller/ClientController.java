package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
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
    public ResponseEntity<Client> addNewClient(@Valid @RequestBody ClientDto client) {
        clientService.addNewClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{clientId}")
    public ResponseEntity<Client> deleteClient(@PathVariable("clientId") Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
