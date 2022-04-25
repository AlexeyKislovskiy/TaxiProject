package fertdt.service.impl;

import fertdt.dto.request.CarRequest;
import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;
import fertdt.exception.duplicatedName.DuplicatedCarNumberException;
import fertdt.exception.notFound.CarClassNotFoundException;
import fertdt.exception.notFound.CarNotFoundException;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.notFound.TaxiParkNotFoundException;
import fertdt.model.CarEntity;
import fertdt.repository.CarClassRepository;
import fertdt.repository.CarRepository;
import fertdt.repository.DriverRepository;
import fertdt.repository.TaxiParkRepository;
import fertdt.service.CarService;
import fertdt.util.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarClassRepository carClassRepository;
    private final TaxiParkRepository taxiParkRepository;
    private final DriverRepository driverRepository;
    private final CarMapper carMapper;

    @Override
    public UUID createCar(PersonalCarRequest car) {
        carRepository.findByNumber(car.getNumber()).ifPresent(s -> {
            throw new DuplicatedCarNumberException();
        });
        carRequestCheck(car);
        driverRepository.findById(car.getOwnerId()).orElseThrow(DriverNotFoundException::new);
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
        driverRepository.findById(car.getOwnerId()).orElseThrow(DriverNotFoundException::new);
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
        carClassRepository.findById(car.getCarClassId()).orElseThrow(CarClassNotFoundException::new);
        taxiParkRepository.findById(car.getTaxiParkId()).orElseThrow(TaxiParkNotFoundException::new);
    }
}
