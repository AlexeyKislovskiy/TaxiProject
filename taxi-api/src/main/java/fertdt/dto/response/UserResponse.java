package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String username;

    private String firstName;

    private String lastName;

    private Double ratingAsPassenger;
}
