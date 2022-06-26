package fertdt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_using")
public class CarUsingEntity extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @Column(name = "to_date", nullable = false)
    private Instant toDate;

    @OneToOne(mappedBy = "carUsing")
    private PaymentForCarUsingEntity payment;
}
