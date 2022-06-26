package fertdt.controller;

import fertdt.api.TaxiRideApi;
import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.security.userdetails.UserAccount;
import fertdt.service.NotificationService;
import fertdt.service.TaxiRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaxiRideController implements TaxiRideApi<UserAccount> {
    private final TaxiRideService taxiRideService;
    private final NotificationService notificationService;

    @Override
    public UUID callTaxi(TaxiCallRequest taxiCallRequest) {
        UUID taxiRideId = taxiRideService.callTaxi(taxiCallRequest);
        notificationService.notifyDriversAboutNewCall(taxiRideId);
        return taxiRideId;
    }

    @Override
    public void cancelTaxiCall(UserAccount user) {
        UUID taxiRideId = taxiRideService.cancelTaxiCall(user.getId());
        notificationService.notifyDriversAboutStopCall(taxiRideId);
    }

    @Override
    public TaxiRideResponse getTaxiRide(UUID taxiRideId) {
        return taxiRideService.getTaxiRideById(taxiRideId);
    }

    @Override
    public void takeOrder(UserAccount driver, UUID taxiRideId) {
        taxiRideService.takeOrder(driver.getId(), taxiRideId);
        notificationService.notifyDriversAboutStopCall(taxiRideId);
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }

    @Override
    public void arriveToClient(UserAccount driver) {
        UUID taxiRideId = taxiRideService.arriveToClient(driver.getId());
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }

    @Override
    public void startTrip(UserAccount driver) {
        UUID taxiRideId = taxiRideService.startTrip(driver.getId());
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }

    @Override
    public void cancelTrip(UserAccount driver) {
        UUID taxiRideId = taxiRideService.cancelTrip(driver.getId());
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }

    @Override
    public void finishTrip(UserAccount driver) {
        UUID taxiRideId = taxiRideService.finishTrip(driver.getId());
        notificationService.notifyPassengerAboutChangeCallStatus(taxiRideId);
    }
}
