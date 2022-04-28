package fertdt.service.impl;

import fertdt.dto.response.VehicleCategoryResponse;
import fertdt.exception.notFound.VehicleCategoryNotFoundException;
import fertdt.repository.VehicleCategoryRepository;
import fertdt.service.VehicleCategoryService;
import fertdt.util.mapper.VehicleCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleCategoryServiceImpl implements VehicleCategoryService {
    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final VehicleCategoryMapper vehicleCategoryMapper;

    @Override
    public VehicleCategoryResponse getVehicleCategoryById(UUID vehicleCategoryId) {
        return vehicleCategoryMapper.toResponse(
                vehicleCategoryRepository.findById(vehicleCategoryId).orElseThrow(VehicleCategoryNotFoundException::new)
        );
    }
}
