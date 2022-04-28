package fertdt.api;

import fertdt.dto.TokenCoupleDto;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/token")
public interface JwtTokenApi {

    @GetMapping(value = "/user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse userInfoByToken(@RequestParam String token);

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse updateTokens(@RequestBody TokenCoupleDto tokenCoupleDto);
}
