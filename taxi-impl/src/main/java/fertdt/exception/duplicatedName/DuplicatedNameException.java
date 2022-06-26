package fertdt.exception.duplicatedName;

import fertdt.exception.ApiException;
import org.springframework.http.HttpStatus;

public class DuplicatedNameException extends ApiException {
    public DuplicatedNameException() {
        this.status = HttpStatus.CONFLICT;
        this.message = "Object with this name already exists, and this violates the logic of the program";
    }
}
