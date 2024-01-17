package SpringBootCarRental.CarRentalSpringBoot.service;

import SpringBootCarRental.CarRentalSpringBoot.converter.ClientConverter;
import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import SpringBootCarRental.CarRentalSpringBoot.exceptions.EmailAlreadyExistsException;
import SpringBootCarRental.CarRentalSpringBoot.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepositoryMock;

    private ClientService clientService;

    static MockedStatic<ClientConverter> mockedClientConverter = Mockito.mockStatic(ClientConverter.class);

    @BeforeEach
    public void setUp() {
        clientService = new ClientService(clientRepositoryMock);
    }

    @Test
    void testCreateClientCallsRepositoryAndConverter() {
        //given
        ClientDto clientDto = new ClientDto("Joaquim", "joaquim@test.com", 635475, 122465233, LocalDate.of(1990, 8, 31));
        Client client = new Client("Joaquim", "joaquim@test.com", 635475, 122465233, LocalDate.of(1990, 8, 31));
        when(clientRepositoryMock.findClientByEmail(clientDto.email())).thenReturn(Optional.empty());
        mockedClientConverter.when(() -> ClientConverter.fromClientDtoToClient(clientDto)).thenReturn(client);
        when(clientRepositoryMock.save(Mockito.any())).thenReturn(client);

        //when
        clientService.addNewClient(clientDto);


        //then
        verify(clientRepositoryMock, times(1)).findClientByEmail(clientDto.email());

        mockedClientConverter.verify(() -> ClientConverter.fromClientDtoToClient(clientDto));
        mockedClientConverter.verifyNoMoreInteractions();

        verify(clientRepositoryMock, times(1)).save(client);
        Mockito.verifyNoMoreInteractions(clientRepositoryMock);
        assertEquals(client, clientService.addNewClient(clientDto));
    }

    @Test
    void createClientWithDuplicatedEmailThrowsException() {
        //given
        ClientDto clientDto = new ClientDto("Joaquim", "joaquim@test.com", 236745, 123435444, LocalDate.of(1990, 8, 31));

        //when
        when(clientRepositoryMock.findClientByEmail(clientDto.email())).thenReturn(Optional.of(new Client()));
        //then
        assertThrows(EmailAlreadyExistsException.class, () -> clientService.addNewClient(clientDto));
        assertEquals("Client Email already exists", assertThrows(EmailAlreadyExistsException.class, () -> clientService.addNewClient(clientDto)).getMessage());

    }


}