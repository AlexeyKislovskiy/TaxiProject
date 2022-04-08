package fertdt.exception.duplicatedName;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Taxi park with this name already exists")
public class DuplicatedTaxiParkNameException extends DuplicatedNameException {
}
