package fertdt.controller;

import fertdt.api.CarClassApi;
import fertdt.dto.response.CarClassResponse;
import fertdt.service.CarClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarClassController implements CarClassApi {
    private final CarClassService carClassService;

    @Override
    public CarClassResponse getCarClass(UUID carClassId) {
        return carClassService.getClassCarById(carClassId);
    }
}
