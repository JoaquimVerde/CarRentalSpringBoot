package SpringBootCarRental.CarRentalSpringBoot.controller;

import SpringBootCarRental.CarRentalSpringBoot.dto.CarDto;
import SpringBootCarRental.CarRentalSpringBoot.repository.CarRepository;
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
class CarControllerTest {

    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void init() {
        carRepository.deleteAll();
        carRepository.resetAutoIncrement();
    }

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Test get all cars when no cars on database returns empty list")
    void testGetAllCarsWhenNoCarsOnDatabaseReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test create a car when car returns status code 201")
    public void testCreateCarReturnCreateAndGetIdEqualsTo1() throws Exception {

        //Given
        String carJson = "{\"brand\": \"Mercedes\", \"licensePlate\": \"44-BB-22\", \"horsePower\": \"1200\", \"km\": \"50000\", \"dailyRate\": \"25\"}";


        //When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isCreated())
                .andReturn();

        //Then
        String responseContent = result.getResponse().getContentAsString();

        CarDto car = objectMapper.readValue(responseContent, CarDto.class);

        //assert car id and name using matchers
        assertThat(car.brand()).isEqualTo("Mercedes");
        assertThat(car.licensePlate()).isEqualTo("44-BB-22");
        assertThat(car.dailyRate()).isEqualTo(25);

    }

    @Test
    @DisplayName("Test get all cars when 2 cars on database returns list with 2 cars")
    void testGetAllCarsWhen2CarsOnDatabaseReturnsListWith2Cars() throws Exception {
        //Create 2 cars
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"Mercedes\", \"licensePlate\": \"44-BB-22\", \"horsePower\": \"1200\", \"km\": \"50000\", \"acquisitionDate\": \"2019-09-13\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"Mercedes\", \"licensePlate\": \"44-CC-22\", \"horsePower\": \"1200\", \"km\": \"51000\", \"acquisitionDate\": \"2020-09-13\"}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        //delete
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cars/1"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cars/2"));
    }


}