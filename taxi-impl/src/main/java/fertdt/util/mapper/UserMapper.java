package fertdt.util.mapper;

import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.response.UserResponse;
import fertdt.model.RatingEntity;
import fertdt.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "hashPassword", source = "password")
    UserEntity toEntity(UserExtendedRequest userRequest);

    @Mapping(target = "ratingAsPassenger", qualifiedByName = "toDoubleValueBySetOfRatings")
    UserResponse toResponse(UserEntity userEntity);

    @Mapping(target = "username", source = "userRequest.username")
    @Mapping(target = "hashPassword", source = "userRequest.password")
    @Mapping(target = "firstName", source = "userRequest.firstName")
    @Mapping(target = "lastName", source = "userRequest.lastName")
    UserEntity toEntity(UserEntity userEntity, UserExtendedRequest userRequest);

    @Named("toDoubleValueBySetOfRatings")
    default Double toDoubleValueBySetOfRatings(Set<RatingEntity> ratings) {
        return ratings.stream().mapToDouble(RatingEntity::getValue).average().orElse(0);
    }
}
