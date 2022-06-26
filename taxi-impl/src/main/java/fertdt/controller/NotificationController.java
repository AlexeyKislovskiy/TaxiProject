package fertdt.controller;

import fertdt.api.NotificationApi;
import fertdt.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class NotificationController implements NotificationApi {
    private final NotificationService notificationService;

    @Override
    public void notifyDriversAboutNewCall(String taxiRideId) {
        notificationService.notifyDriversAboutNewCall(UUID.fromString(taxiRideId));
    }

    @Override
    public void notifyDriversAboutStopCall(String taxiRideId) {
        notificationService.notifyDriversAboutStopCall(UUID.fromString(taxiRideId));
    }

    @Override
    public void notifyPassengerAboutChangeCallStatus(String taxiRideId) {
        notificationService.notifyPassengerAboutChangeCallStatus(UUID.fromString(taxiRideId));
    }
}
