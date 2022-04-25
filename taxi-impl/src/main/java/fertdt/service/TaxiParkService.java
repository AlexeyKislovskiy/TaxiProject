package fertdt.service;

import fertdt.dto.request.TaxiParkRequest;
import fertdt.dto.response.TaxiParkResponse;

import java.util.UUID;

public interface TaxiParkService {
    UUID createTaxiPark(TaxiParkRequest taxiPark);

    TaxiParkResponse getTaxiParkById(UUID taxiParkId);

    void deleteTaxiPark(UUID taxiParkId);

    TaxiParkResponse updateTaxiParkById(UUID taxiParkId, TaxiParkRequest taxiPark);
}
