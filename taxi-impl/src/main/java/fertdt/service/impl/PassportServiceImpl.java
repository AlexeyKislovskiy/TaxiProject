package fertdt.service.impl;

import fertdt.dto.request.PassportRequest;
import fertdt.dto.response.DriverResponse;
import fertdt.dto.response.PassportResponse;
import fertdt.exception.duplicatedName.DuplicatedPassportSeriesAndNumberException;
import fertdt.exception.notFound.PassportNotFoundException;
import fertdt.model.DriverEntity;
import fertdt.model.DriverStatus;
import fertdt.model.PassportEntity;
import fertdt.repository.PassportRepository;
import fertdt.service.DriverService;
import fertdt.service.PassportService;
import fertdt.util.mapper.PassportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final DriverService driverService;

    @Override
    public UUID addPassport(PassportRequest passport) {
        passportRepository.findBySeriesAndNumber(passport.getSeries(), passport.getNumber()).ifPresent(s -> {
            throw new DuplicatedPassportSeriesAndNumberException();
        });
        DriverResponse driver = driverService.getDriverById(passport.getDriverId());
        if (!driver.getDriverLicenses().isEmpty())
            driverService.setDriverStatus(passport.getDriverId(), DriverStatus.DOCUMENTS_NOT_VERIFIED);
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
        DriverEntity driver = passport.getDriver();
        if (driver.getPassports().size() == 1)
            driverService.setDriverStatus(driver.getUuid(), DriverStatus.NO_REQUIRED_DOCUMENTS);
        passportRepository.delete(passport);
    }
}
