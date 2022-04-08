package fertdt.service;

import fertdt.dto.request.CarRequest;
import fertdt.dto.response.CarResponse;

import java.util.UUID;

public interface CarService {
    UUID createCar(CarRequest car);

    CarResponse getCarById(UUID carId);

    void deleteCar(UUID carId);

    CarResponse updateCarById(UUID carId, CarRequest car);
}
