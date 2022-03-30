package fertdt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver")
public class DriverEntity extends UserEntity{
    @ManyToMany
    @JoinTable(
            name = "rating",
            joinColumns = @JoinColumn(name = "get_rating_user_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "put_rating_user_id", referencedColumnName = "uuid"))
    private Set<RatingEntity> getRatings;

    @ManyToMany
    @JoinTable(
            name = "rating",
            joinColumns = @JoinColumn(name = "put_rating_user_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "get_rating_user_id", referencedColumnName = "uuid"))
    private Set<RatingEntity> putRatings;
}
