package fertdt.service.impl;

import fertdt.dto.response.CarClassResponse;
import fertdt.exception.notFound.CarClassNotFoundException;
import fertdt.repository.CarClassRepository;
import fertdt.service.CarClassService;
import fertdt.util.mapper.CarClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarClassServiceImpl implements CarClassService {
    private final CarClassRepository carClassRepository;
    private final CarClassMapper carClassMapper;

    @Override
    public CarClassResponse getClassCarById(UUID carClassId) {
        return carClassMapper.toResponse(
                carClassRepository.findById(carClassId).orElseThrow(CarClassNotFoundException::new)
        );
    }
}
