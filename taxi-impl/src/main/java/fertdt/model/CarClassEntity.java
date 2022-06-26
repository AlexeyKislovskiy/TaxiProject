package fertdt.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_class")
public class CarClassEntity extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "free_waiting", nullable = false)
    private Integer freeWaitingMinutes;

    @Column(name = "paid_waiting", nullable = false)
    private Integer paidWaitingPriceForMinute;

    @Column(name = "minimum_price", nullable = false)
    private Integer minimumPrice;

    @Column(name = "minute_price", nullable = false)
    private Integer pricePerMinute;

    @Column(name = "kilometer_price", nullable = false)
    private Integer pricePerKilometer;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "carClass")
    private Set<CarEntity> cars;
}
