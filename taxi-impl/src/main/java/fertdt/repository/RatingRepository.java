package fertdt.repository;

import fertdt.model.RatingEntity;
import fertdt.model.RatingStatus;
import fertdt.model.TaxiRideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<RatingEntity, UUID> {
    Optional<RatingEntity> findByTaxiRideAndStatus(TaxiRideEntity taxiRide, RatingStatus status);
}
