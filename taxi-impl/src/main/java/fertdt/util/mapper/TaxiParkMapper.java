package fertdt.util.mapper;

import fertdt.dto.request.TaxiParkRequest;
import fertdt.dto.response.TaxiParkResponse;
import fertdt.model.TaxiParkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaxiParkMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    TaxiParkEntity toEntity(TaxiParkRequest taxiParkRequest);

    TaxiParkResponse toResponse(TaxiParkEntity taxiParkEntity);

    @Mapping(target = "name", source = "taxiParkRequest.name")
    @Mapping(target = "commissionPercentage", source = "taxiParkRequest.commissionPercentage")
    TaxiParkEntity toEntity(TaxiParkEntity taxiParkEntity, TaxiParkRequest taxiParkRequest);
}
