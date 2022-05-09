package fertdt.service;

import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.model.DriverEntity;
import fertdt.model.DriverStatus;
import fertdt.model.TaxiRideEntity;

import java.util.UUID;

public interface TaxiRideService {
    UUID callTaxi(TaxiCallRequest taxiCallRequest);

    void cancelTaxiCall(UUID userId);

    TaxiRideResponse getTaxiRideById(UUID taxiRideId);

    TaxiRideEntity getCurrentTaxiRideForUser(UUID userId);

    boolean userHasUnfinishedTrip(UUID userId);
}
