package fertdt.controller;

import fertdt.api.CarUsingApi;
import fertdt.dto.request.CarUsingRequest;
import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.AvailableCarsResponse;
import fertdt.dto.response.CarUsingResponse;
import fertdt.security.userdetails.UserAccount;
import fertdt.service.CarUsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarUsingController implements CarUsingApi<UserAccount> {
    private final CarUsingService carUsingService;

    @Override
    public CarUsingResponse getCarUsing(UUID carUsingId) {
        return carUsingService.getCarUsingById(carUsingId);
    }


    @Override
    public AvailableCarsResponse getAvailableCars(UserAccount driver) {
        return carUsingService.getAvailableCars(driver.getId());
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
    public void stopUsingCar(UserAccount driver) {
        carUsingService.stopUsingCar(driver.getId());
    }
}
