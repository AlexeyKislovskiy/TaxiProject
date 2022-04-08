package fertdt.repository;

import fertdt.model.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverRepository extends JpaRepository<DriverEntity, UUID> {
}
