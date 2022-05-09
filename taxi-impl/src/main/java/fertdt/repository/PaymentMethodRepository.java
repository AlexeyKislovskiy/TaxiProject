package fertdt.repository;

import fertdt.model.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, UUID> {
}
