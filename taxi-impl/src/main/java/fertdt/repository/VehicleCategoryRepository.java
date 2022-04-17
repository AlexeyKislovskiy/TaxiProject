package fertdt.repository;

import fertdt.model.VehicleCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleCategoryRepository extends JpaRepository<VehicleCategoryEntity, UUID> {
}
