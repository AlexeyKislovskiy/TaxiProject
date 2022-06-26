package fertdt.service;

import fertdt.dto.PredictedTripDto;
import fertdt.dto.request.UpcomingTaxiCallRequest;

import java.util.UUID;

public interface TripPredictionService {
    PredictedTripDto predictTrip(UpcomingTaxiCallRequest taxiCallRequest);

    Integer predictTimeToDriver(UUID userId);
}
