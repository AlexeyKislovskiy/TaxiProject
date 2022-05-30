package fertdt.util.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fertdt.dto.PredictedTripDto;
import fertdt.dto.response.CarClassResponse;
import fertdt.exception.JsonException;
import fertdt.util.mapper.TripPredictionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripPredictionMapperImpl implements TripPredictionMapper {
    private final ObjectMapper objectMapper;
    private final String DISTANCES = "distances";
    private final String DURATIONS = "durations";
    private final String TRIPS = "trips";
    private final String DISTANCE = "distance";
    private final String DURATION = "duration";

    @Override
    public PredictedTripDto toResponseFromOrdered(String json, CarClassResponse carClass) {
        int totalDistance = 0, predictedTripTime = 0;
        try {
            JsonNode mainNode = objectMapper.readTree(json);
            JsonNode distanceNode = mainNode.get(DISTANCES), durationNode = mainNode.get(DURATIONS);
            for (int i = 0; i < distanceNode.size() - 1; i++) {
                double distance = distanceNode.get(i).get(i + 1).asDouble(), time = durationNode.get(i).get(i + 1).asDouble();
                totalDistance += (int) (distance / 1000);
                predictedTripTime += (int) (time / 60);
            }
            return PredictedTripDto.builder()
                    .predictedTripTime(predictedTripTime)
                    .totalDistance(totalDistance)
                    .tripPrice(carClass == null ? null : calculateTripPrice(totalDistance, predictedTripTime, carClass))
                    .build();
        } catch (JsonProcessingException e) {
            throw new JsonException("Problem with parsing json, cannot get predicted trip");
        }
    }

    @Override
    public PredictedTripDto toResponseFromOptimized(String json, CarClassResponse carClass) {
        try {
            JsonNode tripsNode = objectMapper.readTree(json).get(TRIPS).get(0);
            double distance = tripsNode.get(DISTANCE).asDouble(), time = tripsNode.get(DURATION).asDouble();
            int totalDistance = (int) (distance / 1000), predictedTripTime = (int) (time / 60);
            return PredictedTripDto.builder()
                    .predictedTripTime(predictedTripTime)
                    .totalDistance(totalDistance)
                    .tripPrice(carClass == null ? null : calculateTripPrice(totalDistance, predictedTripTime, carClass))
                    .build();
        } catch (JsonProcessingException e) {
            throw new JsonException("Problem with parsing json, cannot get predicted trip");
        }
    }

    private int calculateTripPrice(int totalDistance, int predictedTripTime, CarClassResponse carClass) {
        int price = totalDistance * carClass.getPricePerKilometer() + predictedTripTime * carClass.getPricePerMinute();
        return Math.max(price, carClass.getMinimumPrice());
    }
}
