package fertdt.service;

import fertdt.dto.response.CarClassResponse;

import java.util.UUID;

public interface CarCLassService {
    CarClassResponse getClassCarById(UUID carClassId);
}
