package fertdt.service;

import fertdt.dto.response.VehicleCategoryResponse;

import java.util.UUID;

public interface VehicleCategoryService {
    VehicleCategoryResponse getVehicleCategoryById(UUID vehicleCategoryId);
}
