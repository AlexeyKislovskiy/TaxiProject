package fertdt.controller;

import fertdt.api.UserApi;
import fertdt.dto.request.GeographicalCoordinatesRequest;
import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.request.UserRequest;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;
import fertdt.service.UserService;
import fertdt.service.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

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
    public void deleteUser(UUID userId) {
        userService.deleteUser(userId);
    }

    @Override
    public UserResponse updateUser(UUID userId, UserExtendedRequest user) {
        return userService.updateUserById(userId, user);
    }

    @Override
    public TokenCoupleResponse login(UserRequest userRequest) {
        return tokenService.generateTokenCouple(userService.login(userRequest));
    }

    @Override
    public void updateCurrentLocation(UUID userId, GeographicalCoordinatesRequest geographicalCoordinatesRequest) {
        userService.updateCurrentLocation(userId, geographicalCoordinatesRequest);
    }
}
