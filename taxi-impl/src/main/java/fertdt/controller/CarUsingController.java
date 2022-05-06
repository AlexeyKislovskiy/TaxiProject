package fertdt.controller;

import fertdt.api.CarUsingApi;
import fertdt.dto.request.CarUsingRequest;
import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.AvailableCarsResponse;
import fertdt.dto.response.CarUsingResponse;
import fertdt.service.CarUsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarUsingController implements CarUsingApi {
    private final CarUsingService carUsingService;

    @Override
    public CarUsingResponse getCarUsing(UUID carUsingId) {
        return carUsingService.getCarUsingById(carUsingId);
    }


    @Override
    public AvailableCarsResponse getAvailableCars(UUID driverId) {
        return carUsingService.getAvailableCars(driverId);
    }

    @Override
    public UUID usePersonalCar(CarUsingRequest carUsingRequest) {
        return carUsingService.usePersonalCar(carUsingRequest);
    }

    @Override
    public UUID useRentedCar(RentedCarUsingRequest carUsingRequest) {
        return carUsingService.useRentedCar(carUsingRequest);
    }

    @Override
    public void stopUsingCar(UUID driverId) {
        carUsingService.stopUsingCar(driverId);
    }
}
