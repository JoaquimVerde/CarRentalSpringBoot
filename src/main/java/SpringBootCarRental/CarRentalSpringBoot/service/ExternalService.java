package SpringBootCarRental.CarRentalSpringBoot.service;

public class ExternalService {
/*
    @Service
    public class ExternalService {

        ObjectMapper objectMapper =  JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        public Student getExternalService(Long id) throws URISyntaxException, IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/api/v1/students/" + id))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                return objectMapper.readValue(response.body(), Student.class);
            }

            return  null;

        }
    }*/

}
