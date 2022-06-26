package fertdt.service.impl;

import fertdt.dto.request.AdditionalRequirementsRequest;
import fertdt.model.TaxiRideEntity;
import fertdt.repository.AdditionalRequirementsRepository;
import fertdt.service.AdditionalRequirementsService;
import fertdt.util.mapper.AdditionalRequirementsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdditionalRequirementsServiceImpl implements AdditionalRequirementsService {
    private final AdditionalRequirementsRepository additionalRequirementsRepository;
    private final AdditionalRequirementsMapper additionalRequirementsMapper;

    @Override
    public UUID createAdditionalRequirements(TaxiRideEntity taxiRide, AdditionalRequirementsRequest additionalRequirements) {
        if (additionalRequirements == null) return null;
        return additionalRequirementsRepository.save(additionalRequirementsMapper.toEntity(taxiRide, additionalRequirements)).getUuid();
    }
}
