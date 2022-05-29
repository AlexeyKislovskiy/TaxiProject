package fertdt.service;

import fertdt.model.TaxiRideEntity;

import java.util.UUID;

public interface UserTaxiRideService {
    TaxiRideEntity getCurrentTaxiRideForUser(UUID userId);

    boolean userHasUnfinishedTrip(UUID userId);

    TaxiRideEntity getCurrentTaxiRideForDriver(UUID driverId);

    boolean driverHasUnfinishedTrip(UUID driverId);
}
