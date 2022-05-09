package fertdt.repository;

import fertdt.model.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<RatingEntity, UUID> {
}
