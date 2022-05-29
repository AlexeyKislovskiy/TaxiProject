package fertdt.api;

import org.springframework.messaging.handler.annotation.MessageMapping;

public interface NotificationApi {

    @MessageMapping("/new-call")
    void notifyDriversAboutNewCall(String taxiRideId);

    @MessageMapping("/stop-call")
    void notifyDriversAboutStopCall(String taxiRideId);

    @MessageMapping("/passenger")
    void notifyPassengerAboutChangeCallStatus(String taxiRideId);
}
