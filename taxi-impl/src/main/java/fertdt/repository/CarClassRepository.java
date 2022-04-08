package fertdt.repository;

import fertdt.model.CarClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarClassRepository extends JpaRepository<CarClassEntity, UUID> {
}
