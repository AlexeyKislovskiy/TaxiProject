package fertdt.repository;

import fertdt.model.TaxiRideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface TaxiRideRepository extends JpaRepository<TaxiRideEntity, UUID> {
    Set<TaxiRideEntity> findAllByPassenger_Uuid(UUID passengerId);
}
