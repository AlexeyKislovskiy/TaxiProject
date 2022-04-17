package fertdt.service;

import fertdt.dto.response.DriverResponse;

import java.util.UUID;

public interface DriverService {
    UUID createDriverAccount(UUID userId);

    DriverResponse getDriverById(UUID driverId);
}
