package fertdt.controller;

import fertdt.api.TaxiRideApi;
import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.service.NotificationService;
import fertdt.service.TaxiRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaxiRideController implements TaxiRideApi {
    private final TaxiRideService taxiRideService;
    private final NotificationService notificationService;

    @Override
    public UUID callTaxi(TaxiCallRequest taxiCallRequest) {
        UUID taxiRideId = taxiRideService.callTaxi(taxiCallRequest);
        notificationService.notifyDriversAboutNewCall(taxiRideId);
        return taxiRideId;
    }

    @Override
    public void cancelTaxiCall(UUID userId) {
        UUID taxiRideId = taxiRideService.cancelTaxiCall(userId);
        notificationService.notifyDriversAboutStopCall(taxiRideId);
    }

    @Override
    public TaxiRideResponse getTaxiRide(UUID taxiRideId) {
        return taxiRideService.getTaxiRideById(taxiRideId);
    }

    @Override
    public void takeOrder(UUID driverId, UUID taxiRideId) {
        taxiRideService.takeOrder(driverId, taxiRideId);
        notificationService.notifyDriversAboutStopCall(taxiRideId);
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }

    @Override
    public void arriveToClient(UUID driverId) {
        UUID taxiRideId = taxiRideService.arriveToClient(driverId);
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }

    @Override
    public void startTrip(UUID driverId) {
        UUID taxiRideId = taxiRideService.startTrip(driverId);
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }

    @Override
    public void cancelTrip(UUID driverId) {
        UUID taxiRideId = taxiRideService.cancelTrip(driverId);
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }
}
