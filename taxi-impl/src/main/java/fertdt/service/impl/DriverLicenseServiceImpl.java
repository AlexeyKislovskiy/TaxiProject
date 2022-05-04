package fertdt.service.impl;

import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;
import fertdt.dto.response.DriverResponse;
import fertdt.exception.duplicatedName.DuplicatedDriverLicenseIdNumberException;
import fertdt.exception.notFound.DriverLicenseNotFoundException;
import fertdt.model.DriverEntity;
import fertdt.model.DriverLicenseEntity;
import fertdt.model.DriverStatus;
import fertdt.repository.DriverLicenseRepository;
import fertdt.service.DriverLicenseService;
import fertdt.service.DriverService;
import fertdt.service.VehicleCategoryService;
import fertdt.util.mapper.DriverLicenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverLicenseServiceImpl implements DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;
    private final DriverLicenseMapper driverLicenseMapper;
    private final DriverService driverService;
    private final VehicleCategoryService vehicleCategoryService;

    @Override
    public UUID addDriverLicense(DriverLicenseRequest driverLicense) {
        driverLicenseRepository.findByIdNumber(driverLicense.getIdNumber()).ifPresent(s -> {
            throw new DuplicatedDriverLicenseIdNumberException();
        });
        DriverResponse driver = driverService.getDriverById(driverLicense.getDriverId());
        driverLicense.getVehicleCategoryIds().forEach(vehicleCategoryService::getVehicleCategoryById);
        if (!driver.getPassports().isEmpty())
            driverService.setDriverStatus(driverLicense.getDriverId(), DriverStatus.DOCUMENTS_NOT_VERIFIED);
        return driverLicenseRepository.save(driverLicenseMapper.toEntity(driverLicense)).getUuid();
    }

    @Override
    public DriverLicenseResponse getDriverLicenseById(UUID driverLicenseId) {
        return driverLicenseMapper.toResponse(
                driverLicenseRepository.findById(driverLicenseId).orElseThrow(DriverLicenseNotFoundException::new)
        );
    }

    @Override
    public void deleteDriverLicense(UUID driverLicenseId) {
        DriverLicenseEntity driverLicense = driverLicenseRepository.findById(driverLicenseId).orElseThrow(DriverLicenseNotFoundException::new);
        DriverEntity driver = driverLicense.getDriver();
        if (driver.getDriverLicenses().size() == 1)
            driverService.setDriverStatus(driver.getUuid(), DriverStatus.NO_REQUIRED_DOCUMENTS);
        driverLicenseRepository.delete(driverLicense);
    }
}
