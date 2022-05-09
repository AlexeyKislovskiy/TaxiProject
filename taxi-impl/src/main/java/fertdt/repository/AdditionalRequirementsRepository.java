package fertdt.repository;

import fertdt.model.AdditionalRequirementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdditionalRequirementsRepository extends JpaRepository<AdditionalRequirementsEntity, UUID> {
}
