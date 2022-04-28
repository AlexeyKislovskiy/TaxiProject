package fertdt.model;


import fertdt.dto.enums.Privilege;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "privileges")
public class PrivilegeEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Privilege privilege;
}
