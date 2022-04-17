package fertdt.controller;

import fertdt.api.DriverApi;
import fertdt.dto.response.DriverResponse;
import fertdt.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DriverController implements DriverApi {
    private final DriverService driverService;

    @Override
    public UUID createDriverAccount(UUID userId) {
        return driverService.createDriverAccount(userId);
    }

    @Override
    public DriverResponse getDriver(UUID driverId) {
        return driverService.getDriverById(driverId);
    }
}
