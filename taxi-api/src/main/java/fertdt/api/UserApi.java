package fertdt.api;

import fertdt.dto.request.GeographicalCoordinatesRequest;
import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.request.UserRequest;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/users")
public interface UserApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createUser(@Valid @RequestBody UserExtendedRequest user);

    @GetMapping(value = "/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse getUser(@PathVariable("user-id") UUID userId);

    @DeleteMapping(value = "/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@PathVariable("user-id") UUID userId);

    @PutMapping(value = "/{user-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse updateUser(@PathVariable("user-id") UUID userId, @Valid @RequestBody UserExtendedRequest user);

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse login(@RequestBody UserRequest userRequest);


    @PutMapping(value = "/location/{user-id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateCurrentLocation(@PathVariable("user-id") UUID userId, @Valid @RequestBody GeographicalCoordinatesRequest geographicalCoordinatesRequest);
}
