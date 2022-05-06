package fertdt.api;

import fertdt.dto.request.CarUsingRequest;
import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.AvailableCarsResponse;
import fertdt.dto.response.CarUsingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/car-using")
public interface CarUsingApi {
    @GetMapping(value = "/{car-using-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarUsingResponse getCarUsing(@PathVariable("car-using-id") UUID carUsingId);


    @GetMapping(value = "/work/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AvailableCarsResponse getAvailableCars(@PathVariable("driver-id") UUID driverId);

    @PostMapping(value = "work/personal")
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID usePersonalCar(@Valid @RequestBody CarUsingRequest carUsingRequest);

    @PostMapping(value = "work/rented")
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID useRentedCar(@Valid @RequestBody RentedCarUsingRequest carUsingRequest);

    @DeleteMapping(value = "work/{driver-id}")
    @ResponseStatus(HttpStatus.OK)
    void stopUsingCar(@PathVariable("driver-id") UUID driverId);
}
