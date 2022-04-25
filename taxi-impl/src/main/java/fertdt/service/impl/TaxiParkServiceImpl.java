package fertdt.service.impl;

import fertdt.dto.request.TaxiParkRequest;
import fertdt.dto.response.TaxiParkResponse;
import fertdt.exception.duplicatedName.DuplicatedTaxiParkNameException;
import fertdt.exception.notFound.TaxiParkNotFoundException;
import fertdt.model.TaxiParkEntity;
import fertdt.repository.TaxiParkRepository;
import fertdt.service.TaxiParkService;
import fertdt.util.mapper.TaxiParkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaxiParkServiceImpl implements TaxiParkService {
    private final TaxiParkRepository taxiParkRepository;
    private final TaxiParkMapper taxiParkMapper;

    @Override
    public UUID createTaxiPark(TaxiParkRequest taxiPark) {
        taxiParkRepository.findByName(taxiPark.getName()).ifPresent(s -> {
            throw new DuplicatedTaxiParkNameException();
        });
        return taxiParkRepository.save(taxiParkMapper.toEntity(taxiPark)).getUuid();
    }

    @Override
    public TaxiParkResponse getTaxiParkById(UUID taxiParkId) {
        return taxiParkMapper.toResponse(
                taxiParkRepository.findById(taxiParkId).orElseThrow(TaxiParkNotFoundException::new)
        );
    }

    @Override
    public void deleteTaxiPark(UUID taxiParkId) {
        TaxiParkEntity taxiPark = taxiParkRepository.findById(taxiParkId).orElseThrow(TaxiParkNotFoundException::new);
        taxiParkRepository.delete(taxiPark);
    }

    @Override
    public TaxiParkResponse updateTaxiParkById(UUID taxiParkId, TaxiParkRequest taxiPark) {
        TaxiParkEntity taxiParkEntity = taxiParkRepository.findById(taxiParkId).orElseThrow(TaxiParkNotFoundException::new);
        if (!taxiParkEntity.getName().equals(taxiPark.getName())) {
            taxiParkRepository.findByName(taxiPark.getName()).ifPresent(s -> {
                throw new DuplicatedTaxiParkNameException();
            });
        }
        return taxiParkMapper.toResponse(
                taxiParkRepository.save(taxiParkMapper.toEntity(taxiParkEntity, taxiPark))
        );
    }
}
