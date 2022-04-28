package fertdt.util.mapper;

import fertdt.model.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")

public interface RatingMapper {
    @Named("toDoubleValueBySetOfRatings")
    default Double toDoubleValueBySetOfRatings(Set<RatingEntity> ratings) {
        return ratings.stream().mapToDouble(RatingEntity::getValue).average().orElse(0);
    }
}
