package fertdt.service.impl;

import fertdt.dto.request.CarRequest;
import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;
import fertdt.dto.response.DriverResponse;
import fertdt.exception.duplicatedName.DuplicatedCarNumberException;
import fertdt.exception.notFound.CarNotFoundException;
import fertdt.exception.relationalshipConflict.TaxiParkOfDriverAndCarDifferentException;
import fertdt.model.CarEntity;
import fertdt.repository.CarRepository;
import fertdt.service.CarCLassService;
import fertdt.service.CarService;
import fertdt.service.DriverService;
import fertdt.service.TaxiParkService;
import fertdt.util.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CarCLassService carCLassService;
    private final TaxiParkService taxiParkService;
    private final DriverService driverService;

    @Override
    public UUID createCar(PersonalCarRequest car) {
        carRepository.findByNumber(car.getNumber()).ifPresent(s -> {
            throw new DuplicatedCarNumberException();
        });
        carRequestCheck(car);
        DriverResponse driver = driverService.getDriverById(car.getOwnerId());
        if (!taxiParkService.getTaxiParkById(car.getTaxiParkId()).equals(driver.getTaxiPark()))
            throw new TaxiParkOfDriverAndCarDifferentException();
        return carRepository.save(carMapper.toEntity(car)).getUuid();
    }

    @Override
    public UUID createCar(RentedCarRequest car) {
        carRepository.findByNumber(car.getNumber()).ifPresent(s -> {
            throw new DuplicatedCarNumberException();
        });
        carRequestCheck(car);
        return carRepository.save(carMapper.toEntity(car)).getUuid();
    }

    @Override
    public CarResponse getCarById(UUID carId) {
        return carMapper.toResponse(
                carRepository.findById(carId).orElseThrow(CarNotFoundException::new)
        );
    }

    @Override
    public void deleteCar(UUID carId) {
        CarEntity car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        carRepository.delete(car);
    }

    @Override
    public CarResponse updateCarById(UUID carId, PersonalCarRequest car) {
        CarEntity carEntity = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        if (!carEntity.getNumber().equals(car.getNumber())) {
            carRepository.findByNumber(car.getNumber()).ifPresent(s -> {
                throw new DuplicatedCarNumberException();
            });
        }
        carRequestCheck(car);
        DriverResponse driver = driverService.getDriverById(car.getOwnerId());
        if (!taxiParkService.getTaxiParkById(car.getTaxiParkId()).equals(driver.getTaxiPark()))
            throw new TaxiParkOfDriverAndCarDifferentException();
        return carMapper.toResponse(carRepository.save(carMapper.toEntity(carEntity, car)));
    }

    @Override
    public CarResponse updateCarById(UUID carId, RentedCarRequest car) {
        CarEntity carEntity = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        if (!carEntity.getNumber().equals(car.getNumber())) {
            carRepository.findByNumber(car.getNumber()).ifPresent(s -> {
                throw new DuplicatedCarNumberException();
            });
        }
        carRequestCheck(car);
        return carMapper.toResponse(carRepository.save(carMapper.toEntity(carEntity, car)));
    }

    private void carRequestCheck(CarRequest car) {
        carCLassService.getClassCarById(car.getCarClassId());
        taxiParkService.getTaxiParkById(car.getTaxiParkId());
    }
}
