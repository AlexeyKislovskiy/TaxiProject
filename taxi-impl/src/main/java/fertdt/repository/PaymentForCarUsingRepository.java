package fertdt.repository;

import fertdt.model.PaymentForCarUsingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentForCarUsingRepository extends JpaRepository<PaymentForCarUsingEntity, UUID> {
}
