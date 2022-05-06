package fertdt.service.impl;

import fertdt.dto.request.CarUsingRequest;
import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.AvailableCarsResponse;
import fertdt.dto.response.CarUsingResponse;
import fertdt.exception.CarUsingException;
import fertdt.exception.DriverAtWorkException;
import fertdt.exception.DriverHasNoContractException;
import fertdt.exception.VerifiedException;
import fertdt.exception.deletion.StopUsingCarException;
import fertdt.exception.notFound.CarNotFoundException;
import fertdt.exception.notFound.CarUsingNotFoundException;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.relationalshipConflict.CarAlreadyInUseException;
import fertdt.exception.relationalshipConflict.DriverAlreadyUseCarException;
import fertdt.model.CarEntity;
import fertdt.model.CarUsingEntity;
import fertdt.model.DriverEntity;
import fertdt.model.DriverStatus;
import fertdt.repository.CarRepository;
import fertdt.repository.CarUsingRepository;
import fertdt.repository.DriverRepository;
import fertdt.repository.PaymentForCarUsingRepository;
import fertdt.service.CarUsingService;
import fertdt.util.CarUsingUtil;
import fertdt.util.DriverStatusUtil;
import fertdt.util.mapper.CarMapper;
import fertdt.util.mapper.CarUsingMapper;
import fertdt.util.mapper.PaymentForCarUsingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarUsingServiceImpl implements CarUsingService {
    private final CarUsingRepository carUsingRepository;
    private final PaymentForCarUsingRepository paymentForCarUsingRepository;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CarUsingMapper carUsingMapper;
    private final PaymentForCarUsingMapper paymentForCarUsingMapper;

    @Override
    public CarUsingResponse getCarUsingById(UUID carUsingId) {
        return carUsingMapper.toResponse(
                carUsingRepository.findById(carUsingId).orElseThrow(CarUsingNotFoundException::new)
        );
    }


    @Override
    public AvailableCarsResponse getAvailableCars(UUID driverId) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        if (DriverStatusUtil.canWork(driver)) {
            Set<CarEntity> personalCars = new HashSet<>(driver.getPersonalCars());
            Set<CarEntity> rentedCars = new HashSet<>(driver.getTaxiPark().getCars());
            rentedCars = rentedCars.stream().filter(car -> car.getOwner() == null).
                    filter(car -> CarUsingUtil.getCurrentDriver(car) == null).collect(Collectors.toSet());
            return AvailableCarsResponse.builder()
                    .personalCars(personalCars.stream().map(carMapper::toResponse).collect(Collectors.toSet()))
                    .rentedCars(rentedCars.stream().map(carMapper::toResponse).collect(Collectors.toSet()))
                    .build();
        } else if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Account not verified, cannot get available cars");
        else throw new DriverHasNoContractException("Driver does not have a contract, cannot get available cars");
    }

    @Override
    public UUID usePersonalCar(CarUsingRequest carUsingRequest) {
        DriverEntity driver = driverRepository.findById(carUsingRequest.getDriverId()).orElseThrow(DriverNotFoundException::new);
        CarEntity car = carRepository.findById(carUsingRequest.getCarId()).orElseThrow(CarNotFoundException::new);
        if (DriverStatusUtil.canWork(driver)) {
            if (!driver.equals(car.getOwner()))
                throw new CarUsingException("Driver cannot use another driver's car. For using rented car use api/car-using/work/rented");
            if (CarUsingUtil.getCurrentCar(driver) != null) throw new DriverAlreadyUseCarException();
            if (CarUsingUtil.getCurrentDriver(car) != null) throw new CarAlreadyInUseException();
            return carUsingRepository.save(carUsingMapper.toEntity(carUsingRequest)).getUuid();
        } else if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Account not verified, cannot use car");
        else throw new DriverHasNoContractException("Driver does not have a contract, cannot use car");
    }

    @Override
    public UUID useRentedCar(RentedCarUsingRequest carUsingRequest) {
        DriverEntity driver = driverRepository.findById(carUsingRequest.getDriverId()).orElseThrow(DriverNotFoundException::new);
        CarEntity car = carRepository.findById(carUsingRequest.getCarId()).orElseThrow(CarNotFoundException::new);
        if (DriverStatusUtil.canWork(driver)) {
            if (car.getOwner() != null)
                throw new CarUsingException("Driver cannot use another driver's car as rented. For using own car use api/car-using/work/personal");
            if (CarUsingUtil.getCurrentCar(driver) != null) throw new DriverAlreadyUseCarException();
            if (CarUsingUtil.getCurrentDriver(car) != null) throw new CarAlreadyInUseException();
            CarUsingEntity carUsing = carUsingMapper.toEntityFromRentedCarUsingRequest(carUsingRequest);
            carUsing = carUsingRepository.getById(carUsingRepository.save(carUsing).getUuid());
            System.out.println(carUsing);
            paymentForCarUsingRepository.save(paymentForCarUsingMapper.toEntity(carUsing, carUsingRequest));
            return carUsing.getUuid();
        } else if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Account not verified, cannot use car");
        else throw new DriverHasNoContractException("Driver does not have a contract, cannot use car");
    }

    @Override
    public void stopUsingCar(UUID driverId) {
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        if (DriverStatusUtil.canWork(driver)) {
            if (driver.getDriverStatus().equals(DriverStatus.AT_WORK))
                throw new DriverAtWorkException("Driver cannot stop using car at work");
            CarUsingEntity currentCarUsing = CarUsingUtil.getCurrentCarUsing(driver);
            if (currentCarUsing == null) throw new StopUsingCarException();
            currentCarUsing.setToDate(Instant.now());
            carUsingRepository.save(currentCarUsing);
        } else if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Account not verified, cannot stop using car");
        else throw new DriverHasNoContractException("Driver does not have a contract, cannot stop using car");
    }
}
