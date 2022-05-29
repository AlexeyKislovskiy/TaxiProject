package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpcomingTaxiCallRequest {
    @NotNull(message = "Passenger id can not be null")
    private UUID passengerId;

    @NotNull(message = "Starting point can not be null")
    private GeographicalCoordinatesRequest startingPoint;

    @Size(max = 10, message = "Number of intermediate points cannot be more than {max}")
    private List<GeographicalCoordinatesRequest> intermediatePoints;

    private Boolean intermediatePointsOrderImportant;

    @NotNull(message = "Destination can not be null")
    private GeographicalCoordinatesRequest destination;

    @NotNull(message = "Car class id can not be null")
    private UUID carClassId;

    @NotNull(message = "Payment method id can not be null")
    private UUID paymentMethodId;

    private AdditionalRequirementsRequest additionalRequirements;
}
