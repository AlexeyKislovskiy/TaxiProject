package fertdt.api;

import org.springframework.messaging.handler.annotation.MessageMapping;

public interface NotificationApi {

    @MessageMapping("/notification/new-call")
    void notifyDriversAboutNewCall(String taxiRideId);

    @MessageMapping("/notification/stop-call")
    void notifyDriversAboutStopCall(String taxiRideId);

    @MessageMapping("/notification/passenger")
    void notifyPassengerAboutChangeCallStatus(String taxiRideId);
}
