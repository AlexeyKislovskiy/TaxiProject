package fertdt.repository;

import fertdt.model.DriverEntity;
import fertdt.model.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<DriverEntity, UUID> {
    @Query(value = "insert into driver(uuid) values (:user_id)", nativeQuery = true)
    @Modifying
    @Transactional
    void createDriverAccount(@Param("user_id") UUID userId);

    List<DriverEntity> findAllByDriverStatus(DriverStatus driverStatus);
}
