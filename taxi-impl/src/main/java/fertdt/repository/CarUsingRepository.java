package fertdt.repository;

import fertdt.model.CarUsingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarUsingRepository extends JpaRepository<CarUsingEntity, UUID> {
}
