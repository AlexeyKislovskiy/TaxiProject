package fertdt.util.mapper;

import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;
import fertdt.model.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DriverMapper.class, CarClassMapper.class, TaxiParkMapper.class})
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
}
