package fertdt.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additional_requirements")
public class AdditionalRequirementsEntity extends AbstractEntity {
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "taxi_ride_id")
    private TaxiRideEntity taxiRide;

    @Column(name = "seats_number")
    private Integer seatsNumber;

    @Column(name = "child_seats_number")
    private Integer childSeatsNumber;

    @Column(name = "comment_to_driver", columnDefinition = "TEXT")
    private String commentToDriver;
}
