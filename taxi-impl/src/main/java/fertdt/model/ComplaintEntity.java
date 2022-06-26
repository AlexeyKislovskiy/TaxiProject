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
@Table(name = "complaint")
public class ComplaintEntity extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ComplaintStatus status;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "taxi_ride_id")
    private TaxiRideEntity taxiRide;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "complaint_by_id", referencedColumnName = "uuid")
    private UserEntity complaintBy;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "complaint_to_id", referencedColumnName = "uuid")
    private UserEntity complaintTo;

    @Column(name = "text", columnDefinition = "TEXT", nullable = false)
    private String text;
}
