package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.RentalPostDto;
import SpringBootCarRental.CarRentalSpringBoot.repository.CarRepository;
import SpringBootCarRental.CarRentalSpringBoot.repository.ClientRepository;
import SpringBootCarRental.CarRentalSpringBoot.repository.RentalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerTest {


    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ClientRepository clientRepository;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void init() {
        rentalRepository.deleteAll();
        rentalRepository.resetAutoIncrement();
        carRepository.deleteAll();
        carRepository.resetAutoIncrement();
        clientRepository.deleteAll();
        clientRepository.resetAutoIncrement();
    }

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Test get all rentals when no rentals on database returns empty list")
    void testGetAllRentalsWhenNoRentalsOnDatabaseReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rentals/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test create a rental when rental returns status code 201")
    public void testCreateRentalReturnCreateAndGetIdEqualsTo1() throws Exception {

        //Given
        String rentalJson = "{\"clientID\": \"1\", \"carID\": \"1\", \"dateOfRental\": \"2024-02-02\", \"returnDate\": \"2024-02-05\"}";
        String carJson = "{\"brand\": \"Mercedes\", \"licensePlate\": \"44-BB-22\", \"horsePower\": \"1200\", \"km\": \"50000\", \"dailyRate\": \"25\"}";
        String clientJson = "{\"name\": \"Joao\", \"email\": \"j@eee.com\", \"driverLicense\": \"45655345\", \"nif\": \"223335111\", \"dateOfBirth\": \"1989-09-13\"}";

        MvcResult resultCar = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isCreated())
                .andReturn();

        MvcResult resultClient = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated())
                .andReturn();

        //When
        MvcResult resultRental = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rentalJson))
                .andExpect(status().isCreated())
                .andReturn();

        //Then
        String responseContent = resultRental.getResponse().getContentAsString();

        RentalPostDto rental = objectMapper.readValue(responseContent, RentalPostDto.class);

        //assert client id and name using matchers
        assertThat(rental.carID()).isEqualTo(1);
        assertThat(rental.clientID()).isEqualTo(1);
        assertThat(rental.returnDate()).isEqualTo("2024-02-05");

    }

    @Test
    @DisplayName("Test get all rentals when 2 rentals on database returns list with 2 rentals")
    void testGetAllRentalsWhen2RentalsOnDatabaseReturnsListWith2Rentals() throws Exception {
        //Create 2 clients
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Joao\", \"email\": \"j@eee.com\", \"driverLicense\": \"45655345\", \"nif\": \"223335111\", \"dateOfBirth\": \"1989-09-13\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Maria\", \"email\": \"s@eee.com\", \"driverLicense\": \"45835345\", \"nif\": \"223345111\", \"dateOfBirth\": \"1991-09-13\"}"));

        //Create 2 cars
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"Mercedes\", \"licensePlate\": \"44-BB-22\", \"horsePower\": \"1200\", \"km\": \"50000\", \"acquisitionDate\": \"2019-09-13\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"Mercedes\", \"licensePlate\": \"44-CC-22\", \"horsePower\": \"1200\", \"km\": \"51000\", \"acquisitionDate\": \"2020-09-13\"}"));

        //Create 2 rentals
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clientID\": \"1\", \"carID\": \"1\", \"dateOfRental\": \"2024-01-26\", \"returnDate\": \"2024-01-27\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clientID\": \"2\", \"carID\": \"2\", \"dateOfRental\": \"2024-01-26\", \"returnDate\": \"2024-01-26\"}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rentals/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        //delete
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/rentals/1"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/rentals/2"));
    }


}