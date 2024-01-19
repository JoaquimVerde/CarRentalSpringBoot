package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.repository.ClientRepository;
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
class ClientControllerTest {

    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientRepository clientRepository;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void init() {
        clientRepository.deleteAll();
        clientRepository.resetAutoIncrement();
    }

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Test get all clients when no clients on database returns empty list")
    void testGetAllClientsWhenNoClientsOnDatabaseReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test create a client when client returns status code 201")
    public void testCreateClientReturnCreateAndGetIdEqualsTo1() throws Exception {

        //Given
        String clientJson = "{\"name\": \"Joao\", \"email\": \"j@eee.com\", \"driverLicense\": \"45655345\", \"nif\": \"223335111\", \"dateOfBirth\": \"1989-09-13\"}";


        //When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated())
                .andReturn();

        //Then
        String responseContent = result.getResponse().getContentAsString();

        ClientDto client = objectMapper.readValue(responseContent, ClientDto.class);

        //assert client id and name using matchers
        assertThat(client.name()).isEqualTo("Joao");
        assertThat(client.email()).isEqualTo("j@eee.com");

    }

    @Test
    @DisplayName("Test get all clients when 2 clients on database returns list with 2 clients")
    void testGetAllStudentsWhen2ClientsOnDatabaseReturnsListWith2Clients() throws Exception {
        //Create 2 students
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Joao\", \"email\": \"j@eee.com\", \"driverLicense\": \"45655345\", \"nif\": \"223335111\", \"dateOfBirth\": \"1989-09-13\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Maria\", \"email\": \"s@eee.com\", \"driverLicense\": \"45835345\", \"nif\": \"223345111\", \"dateOfBirth\": \"1991-09-13\"}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        //delete
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/1"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/2"));
    }

}
