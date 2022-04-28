package fertdt.repository;

import fertdt.dto.enums.Privilege;
import fertdt.model.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, UUID> {

    PrivilegeEntity findByPrivilege(Privilege privilege);

    Set<PrivilegeEntity> findByPrivilegeIsIn(Set<Privilege> privileges);
}
