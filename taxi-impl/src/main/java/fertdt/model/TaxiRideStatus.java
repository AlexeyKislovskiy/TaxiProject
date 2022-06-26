package fertdt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaxiRideStatus {
    DRIVER_SEARCH("The user called a taxi, the driver is searching"),
    WAITING_FOR_DRIVER_ARRIVING("The driver has been found and is heading to the client"),
    WAITING_FOR_CLIENT("The driver has arrived and is waiting for the client"),
    TRIP_IN_PROGRESS("The driver has picked up the client and is heading to the destination"),
    TRIP_IS_OVER("The driver successfully completed the trip"),
    TRIP_CANCELED_BY_DRIVER("The trip was canceled by the driver"),
    TRIP_CANCELED_BY_CLIENT("The trip was canceled by the client");

    private final String description;
}
