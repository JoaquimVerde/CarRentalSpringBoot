package SpringBootCarRental.CarRentalSpringBoot.mapper;

import SpringBootCarRental.CarRentalSpringBoot.dto.ClientDto;
import SpringBootCarRental.CarRentalSpringBoot.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto fromClientToClientDto(Client client);
    List<ClientDto> ListClientToListClientDto(List<Client> clients);
    Client fromClientDtoToClient(ClientDto client);
}
