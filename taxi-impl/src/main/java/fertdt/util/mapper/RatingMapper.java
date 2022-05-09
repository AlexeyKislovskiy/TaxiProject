package fertdt.util.mapper;

import fertdt.dto.response.RatingResponse;
import fertdt.model.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")

public interface RatingMapper {
    RatingResponse toResponse(RatingEntity ratingEntity);

    @Named("toDoubleValueBySetOfRatings")
    default Double toDoubleValueBySetOfRatings(Set<RatingEntity> ratings) {
        return ratings.stream().mapToDouble(RatingEntity::getValue).average().orElse(0);
    }
}
