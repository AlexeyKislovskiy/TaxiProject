package fertdt.util.mapper;

import fertdt.dto.request.RatingRequest;
import fertdt.dto.response.RatingResponse;
import fertdt.model.RatingEntity;
import fertdt.model.RatingStatus;
import fertdt.model.TaxiRideEntity;
import fertdt.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")

public interface RatingMapper {
    RatingResponse toResponse(RatingEntity ratingEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "value", source = "ratingRequest.value")
    @Mapping(target = "taxiRide", source = "taxiRide")
    @Mapping(target = "ratedBy", expression = "java(getRatedBy(ratingRequest,taxiRide))")
    @Mapping(target = "ratedTo", expression = "java(getRatedTo(ratingRequest,taxiRide))")
    @Mapping(target = "status", expression = "java(getStatus(ratingRequest,taxiRide))")
    RatingEntity toEntity(RatingRequest ratingRequest, TaxiRideEntity taxiRide);

    @Named("toDoubleValueBySetOfRatings")
    default Double toDoubleValueBySetOfRatings(Set<RatingEntity> ratings) {
        return ratings.stream().mapToDouble(RatingEntity::getValue).average().orElse(0);
    }

    @Named("getRatedBy")
    default UserEntity getRatedBy(RatingRequest ratingRequest, TaxiRideEntity taxiRide) {
        if (taxiRide.getPassenger().getUuid().equals(ratingRequest.getUserId())) return taxiRide.getPassenger();
        else return taxiRide.getDriver();
    }

    @Named("getRatedTo")
    default UserEntity getRatedTo(RatingRequest ratingRequest, TaxiRideEntity taxiRide) {
        if (taxiRide.getPassenger().getUuid().equals(ratingRequest.getUserId())) return taxiRide.getDriver();
        else return taxiRide.getPassenger();
    }

    @Named("getStatus")
    default RatingStatus getStatus(RatingRequest ratingRequest, TaxiRideEntity taxiRide) {
        if (taxiRide.getPassenger().getUuid().equals(ratingRequest.getUserId()))
            return RatingStatus.PASSENGER_TO_DRIVER;
        else return RatingStatus.DRIVER_TO_PASSENGER;
    }
}
