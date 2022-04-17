package fertdt.repository;

import fertdt.model.DriverLicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DriverLicenseRepository extends JpaRepository<DriverLicenseEntity, UUID> {
    Optional<DriverLicenseEntity> findByIdNumber(String idNumber);
}
