package fertdt.util.mapper;

import fertdt.dto.response.DriverResponse;
import fertdt.model.DriverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {PassportMapper.class, DriverLicenseMapper.class, RatingMapper.class})
public interface DriverMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "ratingAsPassenger", qualifiedByName = "toDoubleValueBySetOfRatings")
    @Mapping(target = "ratingAsDriver", qualifiedByName = "toDoubleValueBySetOfRatings")
    DriverResponse toResponse(DriverEntity driverEntity);

    @Named("toDriverEntityById")
    default DriverEntity toDriverEntityById(UUID id) {
        return DriverEntity.builder().uuid(id).build();
    }
}
