package fertdt.exception;

import org.springframework.http.HttpStatus;

public class VerifiedException extends ApiException {
    public VerifiedException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
