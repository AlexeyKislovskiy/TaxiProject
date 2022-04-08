package fertdt.exception.notFound;

import fertdt.exception.notFound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Taxi park not found")
public class TaxiParkNotFoundException extends NotFoundException {
}
