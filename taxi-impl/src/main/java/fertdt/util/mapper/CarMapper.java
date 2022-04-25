package fertdt.util.mapper;

import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;
import fertdt.model.CarClassEntity;
import fertdt.model.CarEntity;
import fertdt.model.DriverEntity;
import fertdt.model.TaxiParkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "carClass", source = "carClassId", qualifiedByName = "toCarClassEntityById")
    @Mapping(target = "taxiPark", source = "taxiParkId", qualifiedByName = "toTaxiParkEntityById")
    @Mapping(target = "owner", source = "ownerId", qualifiedByName = "toDriverEntityById")
    CarEntity toEntity(PersonalCarRequest carRequest);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "carClass", source = "carClassId", qualifiedByName = "toCarClassEntityById")
    @Mapping(target = "taxiPark", source = "taxiParkId", qualifiedByName = "toTaxiParkEntityById")
    CarEntity toEntity(RentedCarRequest carRequest);

    @Mapping(target = "carClass", source = "carEntity.carClass.name")
    @Mapping(target = "taxiPark", source = "carEntity.taxiPark.name")
    @Mapping(target = "owner", source = "carEntity.owner.username")
    CarResponse toResponse(CarEntity carEntity);

    @Mapping(target = "number", source = "carRequest.number")
    @Mapping(target = "model", source = "carRequest.model")
    @Mapping(target = "color", source = "carRequest.color")
    @Mapping(target = "seatsNumber", source = "carRequest.seatsNumber")
    @Mapping(target = "childSeatsNumber", source = "carRequest.childSeatsNumber")
    @Mapping(target = "manufactureYear", source = "carRequest.manufactureYear")
    @Mapping(target = "carClass", source = "carRequest.carClassId", qualifiedByName = "toCarClassEntityById")
    @Mapping(target = "taxiPark", source = "carRequest.taxiParkId", qualifiedByName = "toTaxiParkEntityById")
    @Mapping(target = "owner", source = "carRequest.ownerId", qualifiedByName = "toDriverEntityById")
    CarEntity toEntity(CarEntity carEntity, PersonalCarRequest carRequest);

    @Mapping(target = "number", source = "carRequest.number")
    @Mapping(target = "model", source = "carRequest.model")
    @Mapping(target = "color", source = "carRequest.color")
    @Mapping(target = "seatsNumber", source = "carRequest.seatsNumber")
    @Mapping(target = "childSeatsNumber", source = "carRequest.childSeatsNumber")
    @Mapping(target = "manufactureYear", source = "carRequest.manufactureYear")
    @Mapping(target = "carClass", source = "carRequest.carClassId", qualifiedByName = "toCarClassEntityById")
    @Mapping(target = "taxiPark", source = "carRequest.taxiParkId", qualifiedByName = "toTaxiParkEntityById")
    @Mapping(target = "dailyRentalPrice", source = "carRequest.dailyRentalPrice")
    CarEntity toEntity(CarEntity carEntity, RentedCarRequest carRequest);

    @Named("toTaxiParkEntityById")
    default TaxiParkEntity toTaxiParkEntityById(UUID id) {
        return TaxiParkEntity.builder().uuid(id).build();
    }

    @Named("toCarClassEntityById")
    default CarClassEntity toCarClassEntityById(UUID id) {
        return CarClassEntity.builder().uuid(id).build();
    }

    @Named("toDriverEntityById")
    default DriverEntity toDriverEntityById(UUID id) {
        return DriverEntity.builder().uuid(id).build();
    }
}
