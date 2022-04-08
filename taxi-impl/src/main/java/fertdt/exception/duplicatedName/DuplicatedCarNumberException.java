package fertdt.exception.duplicatedName;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Car with this number already exists")
public class DuplicatedCarNumberException extends DuplicatedNameException{
}
