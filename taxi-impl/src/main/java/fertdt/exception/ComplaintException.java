package fertdt.exception;

import org.springframework.http.HttpStatus;

public class ComplaintException extends ApiException {
    public ComplaintException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
