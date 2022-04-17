package fertdt.api;

import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/driver-licenses")
public interface DriverLicenseApi {
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID addDriverLicense(@Valid @RequestBody DriverLicenseRequest driverLicense);

    @GetMapping(value = "/{driver-license-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    DriverLicenseResponse getDriverLicense(@PathVariable("driver-license-id") UUID driverLicenseId);

    @DeleteMapping(value = "/{driver-license-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteDriverLicense(@PathVariable("driver-license-id") UUID driverLicenseId);
}
