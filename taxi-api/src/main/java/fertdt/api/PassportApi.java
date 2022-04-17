package fertdt.api;

import fertdt.dto.request.PassportRequest;
import fertdt.dto.response.PassportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/passports")
public interface PassportApi {
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID addPassport(@Valid @RequestBody PassportRequest passport);

    @GetMapping(value = "/{passport-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PassportResponse getPassport(@PathVariable("passport-id") UUID passportId);

    @DeleteMapping(value = "/{passport-id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePassport(@PathVariable("passport-id") UUID passportId);
}
