package fertdt.controller;

import fertdt.api.CarClassApi;
import fertdt.dto.response.CarClassResponse;
import fertdt.service.CarCLassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarClassController implements CarClassApi {
    private final CarCLassService carCLassService;

    @Override
    public CarClassResponse getCarClass(UUID carClassId) {
        return carCLassService.getClassCarById(carClassId);
    }
}
