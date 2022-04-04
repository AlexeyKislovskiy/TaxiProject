package fertdt.util.mapper;

import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.response.UserResponse;
import fertdt.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "hashPassword", source = "password")
    UserEntity toEntity(UserExtendedRequest userRequest);

    UserResponse toResponse(UserEntity userEntity);

    @Mapping(target = "username", source = "userRequest.username")
    @Mapping(target = "hashPassword", source = "userRequest.password")
    @Mapping(target = "firstName",source = "userRequest.firstName")
    @Mapping(target = "lastName",source = "userRequest.lastName")
    UserEntity toEntity(UserEntity userEntity, UserExtendedRequest userRequest);
}
