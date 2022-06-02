package fertdt.controller;

import fertdt.api.DriverApi;
import fertdt.dto.response.DriverResponse;
import fertdt.security.userdetails.UserAccount;
import fertdt.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DriverController implements DriverApi<UserAccount> {
    private final DriverService driverService;

    @Override
    public void createDriverAccount(UserAccount user) {
        driverService.createDriverAccount(user.getId());
    }

    @Override
    public DriverResponse getDriver(UUID driverId) {
        return driverService.getDriverById(driverId);
    }

    @Override
    public void verifyDriverAccount(UUID driverId) {
        driverService.verifyDriverAccount(driverId);
    }

    @Override
    public void signContractWithTaxiPark(UUID driverId, UUID taxiParkId) {
        driverService.signContractWithTaxiPark(driverId, taxiParkId);
    }

    @Override
    public void breakContractWithTaxiPark(UUID driverId) {
        driverService.breakContractWithTaxiPark(driverId);
    }

    @Override
    public void startWork(UserAccount driver) {
        driverService.startWork(driver.getId());
    }

    @Override
    public void stopWork(UserAccount driver) {
        driverService.stopWork(driver.getId());
    }
}
