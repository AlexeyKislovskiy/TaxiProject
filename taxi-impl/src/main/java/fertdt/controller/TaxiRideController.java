package fertdt.controller;

import fertdt.api.TaxiRideApi;
import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.service.TaxiRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaxiRideController implements TaxiRideApi {
    private final TaxiRideService taxiRideService;

    @Override
    public UUID callTaxi(TaxiCallRequest taxiCallRequest) {
        return taxiRideService.callTaxi(taxiCallRequest);
    }

    @Override
    public void cancelTaxiCall(UUID userId) {
        taxiRideService.cancelTaxiCall(userId);
    }

    @Override
    public TaxiRideResponse getTaxiRide(UUID taxiRideId) {
        return taxiRideService.getTaxiRideById(taxiRideId);
    }
}
