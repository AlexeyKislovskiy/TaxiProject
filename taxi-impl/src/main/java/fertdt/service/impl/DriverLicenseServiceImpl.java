package fertdt.service.impl;

import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;
import fertdt.exception.duplicatedName.DuplicatedDriverLicenseIdNumberException;
import fertdt.exception.notFound.DriverLicenseNotFoundException;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.notFound.VehicleCategoryNotFoundException;
import fertdt.exception.relationalshipConflict.DriverAlreadyHasDriverLicenseException;
import fertdt.model.DriverEntity;
import fertdt.model.DriverLicenseEntity;
import fertdt.repository.DriverLicenseRepository;
import fertdt.repository.DriverRepository;
import fertdt.repository.VehicleCategoryRepository;
import fertdt.service.DriverLicenseService;
import fertdt.util.mapper.DriverLicenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverLicenseServiceImpl implements DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;
    private final DriverRepository driverRepository;
    private final DriverLicenseMapper driverLicenseMapper;
    private final VehicleCategoryRepository vehicleCategoryRepository;

    @Override
    public UUID addDriverLicense(DriverLicenseRequest driverLicense) {
        driverLicenseRepository.findByIdNumber(driverLicense.getIdNumber()).ifPresent(s -> {
            throw new DuplicatedDriverLicenseIdNumberException();
        });
        DriverEntity driver = driverRepository.findById(driverLicense.getDriverId()).orElseThrow(DriverNotFoundException::new);
        if (driver.getDriverLicense() != null) throw new DriverAlreadyHasDriverLicenseException();
        driverLicense.getVehicleCategoryIds().forEach(vehicleCategoryId -> vehicleCategoryRepository.
                findById(vehicleCategoryId).orElseThrow(VehicleCategoryNotFoundException::new));
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
        driverLicenseRepository.delete(driverLicense);
    }
}
