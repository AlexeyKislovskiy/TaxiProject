package fertdt.service;

import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;

import java.util.UUID;

public interface DriverLicenseService {
    UUID addDriverLicense(DriverLicenseRequest driverLicense);

    DriverLicenseResponse getDriverLicenseById(UUID driverLicenseId);

    void deleteDriverLicense(UUID driverLicenseId);
}
