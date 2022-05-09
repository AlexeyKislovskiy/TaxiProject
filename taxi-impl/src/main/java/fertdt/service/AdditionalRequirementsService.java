package fertdt.service;

import fertdt.dto.request.AdditionalRequirementsRequest;
import fertdt.model.TaxiRideEntity;

import java.util.UUID;

public interface AdditionalRequirementsService {
    UUID createAdditionalRequirements(TaxiRideEntity taxiRide, AdditionalRequirementsRequest additionalRequirements);
}
