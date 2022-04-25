package fertdt.repository;

import fertdt.model.TaxiParkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaxiParkRepository extends JpaRepository<TaxiParkEntity, UUID> {
    Optional<TaxiParkEntity> findByName(String name);
}
