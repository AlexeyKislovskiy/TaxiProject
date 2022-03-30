package fertdt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class RatingEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "put_rating_user_id", referencedColumnName = "uuid")
    private UserEntity putRatingUser;

    @ManyToOne
    @JoinColumn(name = "get_rating_user_id", referencedColumnName = "uuid")
    private UserEntity getRatingUser;

    private Integer value;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        DRIVER_TO_PASSENGER,
        PASSENGER_TO_DRIVER
    }
}
