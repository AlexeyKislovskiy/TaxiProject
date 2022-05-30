package fertdt.exception;

import org.springframework.http.HttpStatus;

public class RatingException extends ApiException {
    public RatingException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
