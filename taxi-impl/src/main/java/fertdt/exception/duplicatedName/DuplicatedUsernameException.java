package fertdt.exception.duplicatedName;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User with this username already exists")
public class DuplicatedUsernameException extends DuplicatedNameException {
}
