package fertdt.controller;

import fertdt.api.CarApi;
import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;
import fertdt.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarController implements CarApi {
    private final CarService carService;

    @Override
    public UUID createPersonalCar(PersonalCarRequest car) {
        return carService.createCar(car);
    }

    @Override
    public UUID createRentedCar(RentedCarRequest car) {
        return carService.createCar(car);
    }

    @Override
    public CarResponse getCar(UUID carId) {
        return carService.getCarById(carId);
    }

    @Override
    public void deleteCar(UUID carId) {
        carService.deleteCar(carId);
    }

    @Override
    public CarResponse updatePersonalCar(UUID carId, PersonalCarRequest car) {
        return carService.updateCarById(carId, car);
    }

    @Override
    public CarResponse updateRentedCar(UUID carId, RentedCarRequest car) {
        return carService.updateCarById(carId, car);
    }
}
