package fertdt.controller;

import fertdt.api.TripPredictionApi;
import fertdt.dto.PredictedTripDto;
import fertdt.dto.request.UpcomingTaxiCallRequest;
import fertdt.security.userdetails.UserAccount;
import fertdt.service.TripPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TripPredictionController implements TripPredictionApi<UserAccount> {
    private final TripPredictionService tripPredictionService;

    @Override
    public PredictedTripDto predictTrip(UpcomingTaxiCallRequest taxiCallRequest) {
        return tripPredictionService.predictTrip(taxiCallRequest);
    }

    @Override
    public Integer predictTimeToDriverArriving(UserAccount user) {
        return tripPredictionService.predictTimeToDriver(user.getId());
    }
}
