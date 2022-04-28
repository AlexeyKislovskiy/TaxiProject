package fertdt.util.mapper;

import fertdt.dto.response.RoleResponse;
import fertdt.model.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = PrivilegeMapper.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper {

    @Mapping(target = "roleDescription", source = "role.description")
    RoleResponse toResponse(RoleEntity role);

    List<RoleResponse> toResponse(List<RoleEntity> roles);
}
