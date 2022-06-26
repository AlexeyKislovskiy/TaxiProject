package fertdt.util.mapper;

import fertdt.dto.request.ComplaintRequest;
import fertdt.dto.request.RatingRequest;
import fertdt.dto.response.ComplaintResponse;
import fertdt.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {
    ComplaintResponse toResponse(ComplaintEntity complaintEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "text", source = "complaintRequest.text")
    @Mapping(target = "taxiRide", source = "taxiRide")
    @Mapping(target = "complaintBy", expression = "java(getComplaintBy(complaintRequest,taxiRide))")
    @Mapping(target = "complaintTo", expression = "java(getComplaintTo(complaintRequest,taxiRide))")
    @Mapping(target = "status", expression = "java(getStatus(complaintRequest,taxiRide))")
    ComplaintEntity toEntity(ComplaintRequest complaintRequest, TaxiRideEntity taxiRide);

    @Named("getComplaintBy")
    default UserEntity getComplaintBy(ComplaintRequest complaintRequest, TaxiRideEntity taxiRide) {
        if (taxiRide.getPassenger().getUuid().equals(complaintRequest.getUserId())) return taxiRide.getPassenger();
        else return taxiRide.getDriver();
    }

    @Named("getComplaintTo")
    default UserEntity getComplaintTo(ComplaintRequest complaintRequest, TaxiRideEntity taxiRide) {
        if (taxiRide.getPassenger().getUuid().equals(complaintRequest.getUserId())) return taxiRide.getDriver();
        else return taxiRide.getPassenger();
    }

    @Named("getStatus")
    default ComplaintStatus getStatus(ComplaintRequest complaintRequest, TaxiRideEntity taxiRide) {
        if (taxiRide.getPassenger().getUuid().equals(complaintRequest.getUserId()))
            return ComplaintStatus.PASSENGER_TO_DRIVER;
        else return ComplaintStatus.DRIVER_TO_PASSENGER;
    }
}
