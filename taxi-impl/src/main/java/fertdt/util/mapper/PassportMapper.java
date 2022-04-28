package fertdt.util.mapper;

import fertdt.dto.request.PassportRequest;
import fertdt.dto.response.PassportResponse;
import fertdt.model.PassportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateMapper.class, DriverMapper.class})
public interface PassportMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "dateOfIssue", qualifiedByName = "toDateByString")
    @Mapping(target = "dateOfBirth", qualifiedByName = "toDateByString")
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "toDriverEntityById")
    PassportEntity toEntity(PassportRequest passportRequest);

    @Mapping(target = "driver", source = "passportEntity.driver.username")
    PassportResponse toResponse(PassportEntity passportEntity);
}
