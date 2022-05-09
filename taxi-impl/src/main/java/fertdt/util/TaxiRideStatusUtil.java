package fertdt.util;

import fertdt.model.TaxiRideEntity;
import fertdt.model.TaxiRideStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TaxiRideStatusUtil {
    public static boolean tripIsOver(TaxiRideEntity taxiRide) {
        return taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.TRIP_IS_OVER) ||
                taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.TRIP_CANCELED_BY_DRIVER) ||
                taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.TRIP_CANCELED_BY_CLIENT);
    }

    public static boolean cancellableByPassenger(TaxiRideEntity taxiRide) {
        return taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.DRIVER_SEARCH) ||
                taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.WAITING_FOR_DRIVER_ARRIVING) ||
                taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.WAITING_FOR_CLIENT);
    }
}
