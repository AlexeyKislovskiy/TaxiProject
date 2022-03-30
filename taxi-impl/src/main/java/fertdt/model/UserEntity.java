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
@Table(name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity extends AbstractEntity {
    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

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
