package fertdt.api;

import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/users")
public interface UserApi {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createUser(@RequestBody UserExtendedRequest user);

    @GetMapping(value = "/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse getUser(@PathVariable("user-id") UUID userId);

    @DeleteMapping(value = "/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@PathVariable("user-id") UUID userId);

    @PutMapping(value = "/{user-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse updateUser(@PathVariable("user-id") UUID userId, @RequestBody UserExtendedRequest user);
}
