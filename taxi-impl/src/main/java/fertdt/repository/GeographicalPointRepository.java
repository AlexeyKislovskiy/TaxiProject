package fertdt.repository;

import fertdt.model.GeographicalPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GeographicalPointRepository extends JpaRepository<GeographicalPointEntity, UUID> {
    Optional<GeographicalPointEntity> findByName(String name);
}
