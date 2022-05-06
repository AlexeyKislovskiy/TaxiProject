package fertdt.util.mapper;

import fertdt.dto.request.CarUsingRequest;
import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.CarUsingResponse;
import fertdt.model.CarUsingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PaymentForCarUsingMapper.class, CarMapper.class, DriverMapper.class, DateMapper.class})
public interface CarUsingMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "car", source = "carId", qualifiedByName = "toCarEntityById")
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "toDriverEntityById")
    @Mapping(target = "toDate", ignore = true)
    CarUsingEntity toEntity(CarUsingRequest carUsingRequest);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "car", source = "carId", qualifiedByName = "toCarEntityById")
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "toDriverEntityById")
    @Mapping(target = "toDate", source = "rentedForDays", qualifiedByName = "plusDaysToNow")
    CarUsingEntity toEntityFromRentedCarUsingRequest(RentedCarUsingRequest carUsingRequest);

    @Mapping(target = "fromDate", source = "createDate")
    CarUsingResponse toResponse(CarUsingEntity carUsingEntity);
}
