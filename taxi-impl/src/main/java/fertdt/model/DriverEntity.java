package fertdt.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver")
public class DriverEntity extends UserEntity {
    @Where(clause = "status='PASSENGER_TO_DRIVER'")
    @OneToMany(mappedBy = "ratedTo")
    private Set<RatingEntity> ratingAsDriver;

    @OneToMany(mappedBy = "owner")
    private Set<CarEntity> personalCars;

    @OneToMany(mappedBy = "driver")
    private Set<DriverLicenseEntity> driverLicenses;

    @OneToMany(mappedBy = "driver")
    private Set<PassportEntity> passports;
}
