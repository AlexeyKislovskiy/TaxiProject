package fertdt.service;

import fertdt.dto.PredictedTripDto;
import fertdt.dto.request.UpcomingTaxiCallRequest;

public interface TripPredictionService {
    PredictedTripDto predictTrip(UpcomingTaxiCallRequest taxiCallRequest);
}
