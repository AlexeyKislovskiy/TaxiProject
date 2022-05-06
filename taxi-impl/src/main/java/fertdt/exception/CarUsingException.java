package fertdt.exception;

import org.springframework.http.HttpStatus;

public class CarUsingException extends ApiException {
    public CarUsingException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
