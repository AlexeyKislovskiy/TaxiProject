package fertdt.exception;

import org.springframework.http.HttpStatus;

public class DriverNotAtWorkException extends ApiException {
    public DriverNotAtWorkException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
