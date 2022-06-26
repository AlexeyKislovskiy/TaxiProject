package fertdt.util.mapper;

import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.model.TaxiRideEntity;
import fertdt.model.TaxiRideStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CarClassMapper.class, PaymentMethodMapper.class,
        GeographicalPointMapper.class, AdditionalRequirementsMapper.class, DriverMapper.class, CarMapper.class,
        ComplaintMapper.class, RatingMapper.class}, imports = {TaxiRideStatus.class})
public interface TaxiRideMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "passenger", source = "taxiCall.passengerId", qualifiedByName = "toUserEntityById")
    @Mapping(target = "intermediatePointsOrderImportant", source = "taxiCall.intermediatePointsOrderImportant")
    @Mapping(target = "carClass", source = "taxiCall.carClassId", qualifiedByName = "toCarClassEntityById")
    @Mapping(target = "paymentMethod", source = "taxiCall.paymentMethodId", qualifiedByName = "toPaymentMethodEntityById")
    @Mapping(target = "additionalRequirements", ignore = true)
    @Mapping(target = "taxiRideStatus", expression = "java(TaxiRideStatus.DRIVER_SEARCH)")
    @Mapping(target = "totalDistance", source = "predictedTrip.totalDistance")
    @Mapping(target = "predictedTripTime", source = "predictedTrip.predictedTripTime")
    @Mapping(target = "tripPrice", source = "predictedTrip.tripPrice")
    TaxiRideEntity toEntity(TaxiCallRequest taxiCallRequest);

    TaxiRideResponse toResponse(TaxiRideEntity taxiRideEntity);
}
