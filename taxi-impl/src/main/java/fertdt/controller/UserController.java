package fertdt.controller;

import fertdt.api.UserApi;
import fertdt.dto.request.GeographicalCoordinatesRequest;
import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.request.UserRequest;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;
import fertdt.security.userdetails.UserAccount;
import fertdt.service.UserService;
import fertdt.service.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi<UserAccount> {

    private final UserService userService;
    private final JwtTokenService tokenService;

    @Override
    public UUID createUser(UserExtendedRequest user) {
        return userService.createUser(user);
    }

    @Override
    public UserResponse getUser(UUID userId) {
        return userService.getUserById(userId);
    }

    @Override
    public void deleteUser(UserAccount user) {
        userService.deleteUser(user.getId());
    }

    @Override
    public UserResponse updateUser(UserAccount currentUser, UserExtendedRequest newUser) {
        return userService.updateUserById(currentUser.getId(), newUser);
    }

    @Override
    public TokenCoupleResponse login(UserRequest userRequest) {
        return tokenService.generateTokenCouple(userService.login(userRequest));
    }

    @Override
    public void updateCurrentLocation(UserAccount user, GeographicalCoordinatesRequest geographicalCoordinatesRequest) {
        userService.updateCurrentLocation(user.getId(), geographicalCoordinatesRequest);
    }
}
