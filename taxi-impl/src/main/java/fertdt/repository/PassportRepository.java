package fertdt.repository;

import fertdt.model.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PassportRepository extends JpaRepository<PassportEntity, UUID> {
    Optional<PassportEntity> findBySeriesAndNumber(String series, String number);
}
