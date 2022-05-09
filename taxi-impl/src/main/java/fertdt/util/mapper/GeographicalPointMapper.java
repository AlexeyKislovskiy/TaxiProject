package fertdt.util.mapper;

import fertdt.dto.response.GeographicalPointResponse;
import fertdt.model.GeographicalPointEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeographicalPointMapper {
    GeographicalPointResponse toResponse(GeographicalPointEntity geographicalPointEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    GeographicalPointEntity toEntity(GeographicalPointResponse geographicalPointResponse);
}
