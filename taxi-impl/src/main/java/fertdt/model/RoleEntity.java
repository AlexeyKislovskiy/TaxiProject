package fertdt.model;

import fertdt.dto.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
public class RoleEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Role role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "role_privilege",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "uuid"))
    private Set<PrivilegeEntity> privileges;
}
