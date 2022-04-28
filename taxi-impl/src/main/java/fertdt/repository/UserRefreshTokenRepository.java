package fertdt.repository;

import fertdt.model.UserRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshTokenEntity, UUID> {
    List<UserRefreshTokenEntity> findAllByAccountUuid(UUID accountUuid);
}
