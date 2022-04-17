package fertdt.service.impl;

import fertdt.dto.request.PassportRequest;
import fertdt.dto.response.PassportResponse;
import fertdt.exception.duplicatedName.DuplicatedPassportSeriesAndNumberException;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.notFound.PassportNotFoundException;
import fertdt.exception.relationalshipConflict.DriverAlreadyHasPassportException;
import fertdt.model.DriverEntity;
import fertdt.model.PassportEntity;
import fertdt.repository.DriverRepository;
import fertdt.repository.PassportRepository;
import fertdt.service.PassportService;
import fertdt.util.mapper.PassportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final DriverRepository driverRepository;
    private final PassportMapper passportMapper;

    @Override
    public UUID addPassport(PassportRequest passport) {
        passportRepository.findBySeriesAndNumber(passport.getSeries(), passport.getNumber()).ifPresent(s -> {
            throw new DuplicatedPassportSeriesAndNumberException();
        });
        DriverEntity driver = driverRepository.findById(passport.getDriverId()).orElseThrow(DriverNotFoundException::new);
        if (driver.getPassport() != null) throw new DriverAlreadyHasPassportException();
        return passportRepository.save(passportMapper.toEntity(passport)).getUuid();
    }

    @Override
    public PassportResponse getPassportById(UUID passportId) {
        return passportMapper.toResponse(
                passportRepository.findById(passportId).orElseThrow(PassportNotFoundException::new)
        );
    }

    @Override
    public void deletePassport(UUID passportId) {
        PassportEntity passport = passportRepository.findById(passportId).orElseThrow(PassportNotFoundException::new);
        passportRepository.delete(passport);
    }
}
