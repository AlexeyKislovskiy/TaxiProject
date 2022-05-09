package fertdt.repository;

import fertdt.model.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComplaintRepository extends JpaRepository<ComplaintEntity, UUID> {
}
