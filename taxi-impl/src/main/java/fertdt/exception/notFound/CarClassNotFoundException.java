package fertdt.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Car class not found")
public class CarClassNotFoundException extends NotFoundException {
}
