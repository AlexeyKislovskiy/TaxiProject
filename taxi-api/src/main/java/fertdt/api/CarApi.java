package fertdt.api;

import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/cars")
public interface CarApi {
    @PostMapping(value = "/personal", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createPersonalCar(@Valid @RequestBody PersonalCarRequest car);

    @PostMapping(value = "/rented", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createRentedCar(@Valid @RequestBody RentedCarRequest car);

    @GetMapping(value = "/{car-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarResponse getCar(@PathVariable("car-id") UUID carId);

    @DeleteMapping(value = "/{car-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCar(@PathVariable("car-id") UUID carId);

    @PutMapping(value = "/personal/{car-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarResponse updatePersonalCar(@PathVariable("car-id") UUID carId, @Valid @RequestBody  PersonalCarRequest car);

    @PutMapping(value = "/rented/{car-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarResponse updateRentedCar(@PathVariable("car-id") UUID carId, @Valid @RequestBody RentedCarRequest car);
}
