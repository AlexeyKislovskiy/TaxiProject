package fertdt.util.mapper;

import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.response.UserResponse;
import fertdt.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, RatingMapper.class})
public interface UserMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "hashPassword", source = "password")
    @Mapping(target = "roles", ignore = true)
    UserEntity toEntity(UserExtendedRequest userRequest);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "ratingAsPassenger", qualifiedByName = "toDoubleValueBySetOfRatings")
    UserResponse toResponse(UserEntity userEntity);

    @Mapping(target = "username", source = "userRequest.username")
    @Mapping(target = "hashPassword", source = "userRequest.password")
    @Mapping(target = "firstName", source = "userRequest.firstName")
    @Mapping(target = "lastName", source = "userRequest.lastName")
    UserEntity toEntity(UserEntity userEntity, UserExtendedRequest userRequest);
}
