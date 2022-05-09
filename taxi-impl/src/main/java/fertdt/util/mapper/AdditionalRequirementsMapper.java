package fertdt.util.mapper;

import fertdt.dto.request.AdditionalRequirementsRequest;
import fertdt.dto.response.AdditionalRequirementsResponse;
import fertdt.model.AdditionalRequirementsEntity;
import fertdt.model.TaxiRideEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdditionalRequirementsMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "taxiRide", source = "taxiRideEntity")
    AdditionalRequirementsEntity toEntity(TaxiRideEntity taxiRideEntity, AdditionalRequirementsRequest additionalRequirementsRequest);

    AdditionalRequirementsResponse toResponse(AdditionalRequirementsEntity additionalRequirementsEntity);
}
