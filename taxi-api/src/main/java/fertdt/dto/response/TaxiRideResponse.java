package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiRideResponse {
    private UserResponse passenger;

    private GeographicalPointResponse startingPoint;

    private List<GeographicalPointResponse> intermediatePoints;

    private Boolean intermediatePointsOrderImportant;

    private GeographicalPointResponse destination;

    private CarClassResponse carClass;

    private PaymentMethodResponse paymentMethod;

    private AdditionalRequirementsResponse additionalRequirements;

    private String taxiRideStatus;

    private DriverResponse driver;

    private CarResponse car;

    private Integer totalDistance;

    private String arrivingTime;

    private String tripStartTime;

    private String tripFinishTime;

    private Integer predictedTripTime;

    private Integer tripPrice;

    private Integer tips;

    private List<RatingResponse> ratingToDriver;

    private List<RatingResponse> ratingToPassenger;

    private List<ComplaintResponse> complaintsToDriver;

    private List<ComplaintResponse> complaintsToPassenger;
}
