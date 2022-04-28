package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;

    private Set<RoleResponse> roles;

    private String username;

    private String firstName;

    private String lastName;

    private Double ratingAsPassenger;
}
