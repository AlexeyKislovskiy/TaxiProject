package fertdt.service;

import fertdt.dto.response.CarClassResponse;

import java.util.UUID;

public interface CarClassService {
    CarClassResponse getClassCarById(UUID carClassId);
}
