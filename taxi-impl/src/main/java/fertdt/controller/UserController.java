package fertdt.controller;

import fertdt.api.UserApi;
import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.response.UserResponse;
import fertdt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public UUID createUser(UserExtendedRequest user) {
        return userService.createUser(user);
    }

    @Override
    public UserResponse getUser(UUID userId) {
        return userService.getUserById(userId);
    }

    @Override
    public void deleteUser(UUID userId) {
        userService.deleteUser(userId);
    }

    @Override
    public UserResponse updateUser(UUID userId, UserExtendedRequest user) {
        return userService.updateUserById(userId, user);
    }
}
