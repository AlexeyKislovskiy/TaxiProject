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
    public UUID createCar(CarRequest car) {
        carRepository.findByNumber(car.getNumber()).ifPresent(s -> {
            throw new DuplicatedCarNumberException();
        });
        carRequestCheck(car);
        if (car instanceof PersonalCarRequest)
            return carRepository.save(carMapper.toEntity((PersonalCarRequest) car)).getUuid();
        else
            return carRepository.save(carMapper.toEntity((RentedCarRequest) car)).getUuid();
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
    public CarResponse updateCarById(UUID carId, CarRequest car) {
        CarEntity carEntity = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        if (!carEntity.getNumber().equals(car.getNumber())) {
            carRepository.findByNumber(car.getNumber()).ifPresent(s -> {
                throw new DuplicatedCarNumberException();
            });
        }
        carRequestCheck(car);
        if (car instanceof PersonalCarRequest)
            return carMapper.toResponse(
                    carRepository.save(carMapper.toEntity(carEntity, (PersonalCarRequest) car))
            );
        else
            return carMapper.toResponse(
                    carRepository.save(carMapper.toEntity(carEntity, (RentedCarRequest) car))
            );
    }

    private void carRequestCheck(CarRequest car) {
        carClassRepository.findById(car.getCarClassId()).orElseThrow(CarClassNotFoundException::new);
        taxiParkRepository.findById(car.getTaxiParkId()).orElseThrow(TaxiParkNotFoundException::new);
        if (car instanceof PersonalCarRequest) {
            PersonalCarRequest personalCarRequest = (PersonalCarRequest) car;
            driverRepository.findById(personalCarRequest.getOwnerId()).orElseThrow(DriverNotFoundException::new);
        }
    }
}
