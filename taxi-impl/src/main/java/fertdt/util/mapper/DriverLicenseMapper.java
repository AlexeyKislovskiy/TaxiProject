package fertdt.util.mapper;

import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;
import fertdt.model.DriverLicenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleCategoryMapper.class, DateMapper.class, DriverMapper.class})
public interface DriverLicenseMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "dateOfIssue", qualifiedByName = "toDateByString")
    @Mapping(target = "dateOfBirth", qualifiedByName = "toDateByString")
    @Mapping(target = "expirationDate", qualifiedByName = "toDateByString")
    @Mapping(target = "vehicleCategories", source = "vehicleCategoryIds", qualifiedByName = "toSetOfVehicleCategoryEntitiesBySetOfIds")
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "toDriverEntityById")
    DriverLicenseEntity toEntity(DriverLicenseRequest driverLicenseRequest);

    @Mapping(target = "driver", source = "driverLicenseEntity.driver.username")
    DriverLicenseResponse toResponse(DriverLicenseEntity driverLicenseEntity);
}
