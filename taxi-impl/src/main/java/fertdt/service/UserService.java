package fertdt.service;

import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {

    UUID createUser(UserExtendedRequest user);

    void deleteUser(UUID userId);

    UserResponse getUserById(UUID userId);

    UserResponse updateUserById(UUID userId, UserExtendedRequest user);
}
