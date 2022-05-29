package fertdt.service.impl;

import fertdt.dto.response.DriverResponse;
import fertdt.exception.*;
import fertdt.exception.deletion.DriverBreakContractException;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.relationalshipConflict.AccountAlreadyVerifiedException;
import fertdt.exception.relationalshipConflict.DriverAlreadySignedContractException;
import fertdt.exception.relationalshipConflict.DriverHasUnfinishedTripAsPassengerException;
import fertdt.exception.relationalshipConflict.UserAlreadyHasDriverAccountException;
import fertdt.model.DriverEntity;
import fertdt.model.DriverStatus;
import fertdt.model.TaxiParkEntity;
import fertdt.repository.DriverRepository;
import fertdt.service.DriverService;
import fertdt.service.TaxiParkService;
import fertdt.service.UserTaxiRideService;
import fertdt.util.CarUsingUtil;
import fertdt.util.DriverStatusUtil;
import fertdt.util.mapper.DriverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final UserServiceImpl userService;
    private final TaxiParkService taxiParkService;
    private final UserTaxiRideService userTaxiRideService;

    @Override
    public void createDriverAccount(UUID userId) {
        userService.getUserById(userId);
        driverRepository.findById(userId).ifPresent(s -> {
            throw new UserAlreadyHasDriverAccountException();
        });
        driverRepository.createDriverAccount(userId);
    }

    @Override
    public DriverResponse getDriverById(UUID driverId) {
        return driverMapper.toResponse(
                driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new)
        );
    }

    @Override
    public DriverEntity setDriverStatus(UUID driverId, DriverStatus driverStatus) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        driver.setDriverStatus(driverStatus);
        return driverRepository.save(driver);
    }

    @Override
    public void verifyDriverAccount(UUID driverId) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        DriverStatus driverStatus = driver.getDriverStatus();
        if (driverStatus.equals(DriverStatus.NO_REQUIRED_DOCUMENTS))
            throw new VerifiedException("Account cannot be verified, the required documents have not been added");
        else if (driverStatus.equals(DriverStatus.DOCUMENTS_NOT_VERIFIED)) {
            if (driver.getTaxiPark() == null) setDriverStatus(driverId, DriverStatus.NO_CONTRACT_WITH_TAXI_PARK);
            else setDriverStatus(driverId, DriverStatus.NOT_AT_WORK);
        } else throw new AccountAlreadyVerifiedException();
    }

    @Override
    public void signContractWithTaxiPark(UUID driverId, UUID taxiParkId) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        taxiParkService.getTaxiParkById(taxiParkId);
        DriverStatus driverStatus = driver.getDriverStatus();
        if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Driver with an unverified documents cannot sign a contract");
        else if (driverStatus.equals(DriverStatus.NO_CONTRACT_WITH_TAXI_PARK)) {
            driver = setDriverStatus(driverId, DriverStatus.NOT_AT_WORK);
            driver.setTaxiPark(TaxiParkEntity.builder().uuid(taxiParkId).build());
            driverRepository.save(driver);
        } else {
            throw new DriverAlreadySignedContractException();
        }
    }

    @Override
    public void breakContractWithTaxiPark(UUID driverId) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        if (driver.getTaxiPark() == null) throw new DriverBreakContractException();
        else {
            driver = setDriverStatus(driverId, DriverStatus.NO_CONTRACT_WITH_TAXI_PARK);
            driver.setTaxiPark(null);
            driverRepository.save(driver);
        }
    }

    @Override
    public void startWork(UUID driverId) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        if (DriverStatusUtil.canWork(driver)) {
            if (driver.getDriverStatus().equals(DriverStatus.AT_WORK))
                throw new DriverAtWorkException("Driver already at work");
            if (CarUsingUtil.getCurrentCar(driver) == null)
                throw new CarUsingException("Driver cannot start work without car");
            if (userTaxiRideService.userHasUnfinishedTrip(driverId))
                throw new DriverHasUnfinishedTripAsPassengerException();
            setDriverStatus(driverId, DriverStatus.AT_WORK);
        } else if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Account not verified, cannot start work");
        else throw new DriverHasNoContractException("Driver does not have a contract, cannot start work");
    }

    @Override
    public void stopWork(UUID driverId) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        if (DriverStatusUtil.canWork(driver)) {
            if (driver.getDriverStatus().equals(DriverStatus.NOT_AT_WORK))
                throw new DriverNotAtWorkException("Driver already not at work");
            setDriverStatus(driverId, DriverStatus.NOT_AT_WORK);
        } else if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Account not verified, cannot stop work");
        else throw new DriverHasNoContractException("Driver does not have a contract, cannot stop work");
    }

    @Override
    public DriverEntity getDriverEntityById(UUID driverId) {
        return driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
    }
}
