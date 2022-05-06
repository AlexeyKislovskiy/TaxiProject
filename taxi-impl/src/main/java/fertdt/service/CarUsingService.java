package fertdt.service;

import fertdt.dto.request.CarUsingRequest;
import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.AvailableCarsResponse;
import fertdt.dto.response.CarUsingResponse;

import java.util.UUID;

public interface CarUsingService {
    CarUsingResponse getCarUsingById(UUID carUsingId);

    AvailableCarsResponse getAvailableCars(UUID driverId);

    UUID usePersonalCar(CarUsingRequest carUsingRequest);

    UUID useRentedCar(RentedCarUsingRequest carUsingRequest);

    void stopUsingCar(UUID driverId);
}
