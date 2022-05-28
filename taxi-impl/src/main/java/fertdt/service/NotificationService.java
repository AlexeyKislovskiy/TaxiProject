package fertdt.service;

import java.util.UUID;

public interface NotificationService {
    void notifyDriversAboutNewCall(UUID taxiRideId);

    void notifyDriversAboutStopCall(UUID taxiRideId);

    void notifyPassengerAboutChangeCallStatus(UUID taxiRideId);
}
