package fertdt.service;

import fertdt.dto.response.DriverResponse;
import fertdt.model.DriverEntity;
import fertdt.model.DriverStatus;

import java.util.UUID;

public interface DriverService {
    void createDriverAccount(UUID userId);

    DriverResponse getDriverById(UUID driverId);

    DriverEntity setDriverStatus(UUID driverId, DriverStatus driverStatus);

    void verifyDriverAccount(UUID driverId);

    void signContractWithTaxiPark(UUID driverId, UUID taxiParkId);

    void breakContractWithTaxiPark(UUID driverId);

    void startWork(UUID driverId);

    void stopWork(UUID driverId);

    DriverEntity getDriverEntityById(UUID driverId);
}
