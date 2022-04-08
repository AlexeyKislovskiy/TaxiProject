package fertdt.controller;

import fertdt.api.TaxiParkApi;
import fertdt.dto.request.TaxiParkRequest;
import fertdt.dto.response.TaxiParkResponse;
import fertdt.service.TaxiParkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaxiParkController implements TaxiParkApi {
    private final TaxiParkService taxiParkService;

    @Override
    public UUID createTaxiPark(TaxiParkRequest taxiPark) {
        return taxiParkService.createTaxiPark(taxiPark);
    }

    @Override
    public TaxiParkResponse getTaxiPark(UUID taxiParkId) {
        return taxiParkService.getTaxiParkById(taxiParkId);
    }

    @Override
    public void deleteTaxiPark(UUID taxiParkId) {
        taxiParkService.deleteTaxiPark(taxiParkId);
    }

    @Override
    public TaxiParkResponse updateTaxiPark(UUID taxiParkId, TaxiParkRequest taxiPark) {
        return taxiParkService.updateTaxiParkById(taxiParkId, taxiPark);
    }
}
