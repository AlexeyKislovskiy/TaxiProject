package fertdt.util.mapper;

import fertdt.dto.response.VehicleCategoryResponse;
import fertdt.model.VehicleCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VehicleCategoryMapper {
    VehicleCategoryResponse toResponse(VehicleCategoryEntity vehicleCategoryEntity);

    @Named("toVehicleCategoryEntityById")
    default VehicleCategoryEntity toVehicleCategoryEntityById(UUID id) {
        return VehicleCategoryEntity.builder().uuid(id).build();
    }

    @Named("toSetOfVehicleCategoryEntitiesBySetOfIds")
    default Set<VehicleCategoryEntity> toSetOfVehicleCategoryEntitiesBySetOfIds(Set<UUID> ids) {
        return ids.stream().map(this::toVehicleCategoryEntityById).collect(Collectors.toSet());
    }
}
