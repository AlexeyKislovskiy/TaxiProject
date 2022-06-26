package fertdt.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class CarEntity extends AbstractEntity {
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "seats_number", nullable = false)
    private Integer seatsNumber;

    @Column(name = "child_seats_number", nullable = false)
    private Integer childSeatsNumber;

    @Column(name = "manufacture_year", nullable = false)
    private Integer manufactureYear;

    @ManyToOne
    @JoinColumn(name = "car_class_id", nullable = false)
    private CarClassEntity carClass;

    @ManyToOne
    @JoinColumn(name = "taxi_park_id", nullable = false)
    private TaxiParkEntity taxiPark;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private DriverEntity owner;

    @Column(name = "daily_rental_price")
    private Integer dailyRentalPrice;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "car")
    private Set<CarUsingEntity> carUsing;
}
