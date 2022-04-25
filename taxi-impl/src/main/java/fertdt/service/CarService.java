package fertdt.service;

import fertdt.dto.request.CarRequest;
import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;

import java.util.UUID;

public interface CarService {
    UUID createCar(PersonalCarRequest car);

    UUID createCar(RentedCarRequest car);

    CarResponse getCarById(UUID carId);

    void deleteCar(UUID carId);

    CarResponse updateCarById(UUID carId, PersonalCarRequest car);

    CarResponse updateCarById(UUID carId, RentedCarRequest car);
}
