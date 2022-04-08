package fertdt.exception.duplicatedName;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Object with this name already exists, and this violates the logic of the program")
public class DuplicatedNameException extends RuntimeException{
}
