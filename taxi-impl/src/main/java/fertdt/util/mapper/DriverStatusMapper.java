package fertdt.util.mapper;

import fertdt.dto.response.DriverStatusResponse;
import fertdt.model.DriverStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverStatusMapper {
    @Mapping(target = "driverStatus", source = "driverStatus")
    @Mapping(target = "driverStatusDescription", source = "driverStatus.description")
    DriverStatusResponse toResponse(DriverStatus driverStatus);
}
