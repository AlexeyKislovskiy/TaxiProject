package fertdt.repository;


import fertdt.dto.enums.Role;
import fertdt.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findByRole(Role role);

    Set<RoleEntity> findByRoleIsIn(Set<Role> roles);
}
