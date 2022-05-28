package fertdt.service;

import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.DriverResponse;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.model.CarEntity;
import fertdt.model.TaxiRideEntity;

import java.util.List;
import java.util.UUID;

public interface TaxiRideService {
    UUID callTaxi(TaxiCallRequest taxiCallRequest);

    UUID cancelTaxiCall(UUID userId);

    TaxiRideResponse getTaxiRideById(UUID taxiRideId);

    TaxiRideEntity getCurrentTaxiRideForUser(UUID userId);

    boolean userHasUnfinishedTrip(UUID userId);

    void takeOrder(UUID driverId, UUID taxiRideId);

    TaxiRideEntity getCurrentTaxiRideForDriver(UUID driverId);

    boolean driverHasUnfinishedTrip(UUID driverId);

    List<DriverResponse> findAllFreeDrivers(TaxiRideEntity taxiRide);

    boolean carFitsCall(CarEntity car, TaxiRideEntity taxiRide);

    TaxiRideEntity getTaxiRideEntityById(UUID taxiRideId);

    void save(TaxiRideEntity taxiRide);

    UUID arriveToClient(UUID driverId);

    UUID startTrip(UUID driverId);

    UUID cancelTrip(UUID driverId);
}