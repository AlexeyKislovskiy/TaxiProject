package fertdt.util.mapper;

import fertdt.dto.PredictedTripDto;
import fertdt.dto.response.CarClassResponse;

public interface TripPredictionMapper {
    PredictedTripDto toResponseFromOrdered(String json, CarClassResponse carClass);

    PredictedTripDto toResponseFromOptimized(String json, CarClassResponse carClass);
}
