package fertdt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taxi_ride")
public class TaxiRideEntity extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private UserEntity passenger;

    @ManyToOne
    @JoinColumn(name = "starting_point_id", nullable = false)
    private GeographicalPointEntity startingPoint;

    @ManyToMany
    @JoinTable(
            name = "taxi_ride_intermediate_points",
            joinColumns = @JoinColumn(name = "taxi_ride_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "intermediate_point_id", referencedColumnName = "uuid"))
    private List<GeographicalPointEntity> intermediatePoints;

    @Column(name = "intermediate_points_order_important")
    private Boolean intermediatePointsOrderImportant;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private GeographicalPointEntity destination;

    @ManyToOne
    @JoinColumn(name = "car_class_id", nullable = false)
    private CarClassEntity carClass;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodEntity paymentMethod;

    @OneToOne(mappedBy = "taxiRide")
    private AdditionalRequirementsEntity additionalRequirements;

    @Enumerated(EnumType.STRING)
    @Column(name = "taxi_ride_status", nullable = false)
    private TaxiRideStatus taxiRideStatus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @Column(name = "total_distance", nullable = false)
    private Integer totalDistance;

    @Column(name = "arriving_time")
    private Instant arrivingTime;

    @Column(name = "trip_start_time")
    private Instant tripStartTime;

    @Column(name = "trip_finish_time")
    private Instant tripFinishTime;

    @Column(name = "predicted_trip_time", nullable = false)
    private Integer predictedTripTime;

    @Column(name = "trip_price")
    private Integer tripPrice;

    @Column(name = "tips")
    private Integer tips;

    @Where(clause = "status='PASSENGER_TO_DRIVER'")
    @OneToMany(mappedBy = "taxiRide")
    private List<RatingEntity> ratingToDriver;

    @Where(clause = "status='DRIVER_TO_PASSENGER'")
    @OneToMany(mappedBy = "taxiRide")
    private List<RatingEntity> ratingToPassenger;

    @Where(clause = "status='PASSENGER_TO_DRIVER'")
    @OneToMany(mappedBy = "taxiRide")
    private List<ComplaintEntity> complaintsToDriver;

    @Where(clause = "status='DRIVER_TO_PASSENGER'")
    @OneToMany(mappedBy = "taxiRide")
    private List<ComplaintEntity> complaintsToPassenger;

    @ManyToMany
    @JoinTable(
            name = "driver_taxi_ride_notification",
            joinColumns = @JoinColumn(name = "taxi_ride_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "uuid"))
    private Set<DriverEntity> notifications;

}
