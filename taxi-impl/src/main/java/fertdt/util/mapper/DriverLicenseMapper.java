package fertdt.util.mapper;

import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;
import fertdt.model.DriverEntity;
import fertdt.model.DriverLicenseEntity;
import fertdt.model.VehicleCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
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
    @Mapping(target = "vehicleCategories", qualifiedByName = "toSetOfVehicleCategoryNamesBySetOfEntities")
    DriverLicenseResponse toResponse(DriverLicenseEntity driverLicenseEntity);

    @Named("toDriverEntityById")
    default DriverEntity toDriverEntityById(UUID id) {
        return DriverEntity.builder().uuid(id).build();
    }

    @Named("toDateByString")
    default LocalDate toDateByString(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.uuuu"));
    }

    @Named("toSetOfVehicleCategoryEntitiesBySetOfIds")
    default Set<VehicleCategoryEntity> toSetOfVehicleCategoryEntitiesBySetOfIds(Set<UUID> vehicleCategoryIds) {
        return vehicleCategoryIds.stream().map(id -> VehicleCategoryEntity.builder().uuid(id).build()).collect(Collectors.toSet());
    }

    @Named("toSetOfVehicleCategoryNamesBySetOfEntities")
    default Set<String> toSetOfVehicleCategoryNamesBySetOfEntities(Set<VehicleCategoryEntity> vehicleCategories) {
        return vehicleCategories.stream().map(VehicleCategoryEntity::getName).collect(Collectors.toSet());
    }
}
