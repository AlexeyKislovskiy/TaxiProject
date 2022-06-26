package fertdt.repository;

import fertdt.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {
    Optional<CarEntity> findByNumber(String number);
}
