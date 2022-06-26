package fertdt.controller;

import fertdt.api.DriverLicenseApi;
import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;
import fertdt.service.DriverLicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DriverLicenseController implements DriverLicenseApi {
    private final DriverLicenseService driverLicenseService;

    @Override
    public UUID addDriverLicense(DriverLicenseRequest driverLicense) {
        return driverLicenseService.addDriverLicense(driverLicense);
    }

    @Override
    public DriverLicenseResponse getDriverLicense(UUID driverLicenseId) {
        return driverLicenseService.getDriverLicenseById(driverLicenseId);
    }

    @Override
    public void deleteDriverLicense(UUID driverLicenseId) {
        driverLicenseService.deleteDriverLicense(driverLicenseId);
    }
}
