package fertdt.util.mapper;

import fertdt.dto.response.DriverResponse;
import fertdt.model.DriverEntity;
import fertdt.model.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    @Mapping(target = "ratingAsPassenger", qualifiedByName = "toDoubleValueBySetOfRatings")
    @Mapping(target = "ratingAsDriver", qualifiedByName = "toDoubleValueBySetOfRatings")
    DriverResponse toResponse(DriverEntity driverEntity);

    @Named("toDoubleValueBySetOfRatings")
    default Double toDoubleValueBySetOfRatings(Set<RatingEntity> ratings) {
        return ratings.stream().mapToDouble(RatingEntity::getValue).average().orElse(0);
    }
}
