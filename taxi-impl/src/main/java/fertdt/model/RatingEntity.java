package fertdt.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class RatingEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "rated_by_id", referencedColumnName = "uuid")
    private UserEntity ratedBy;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "rated_to_id", referencedColumnName = "uuid")
    private UserEntity ratedTo;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RatingStatus status;
}
