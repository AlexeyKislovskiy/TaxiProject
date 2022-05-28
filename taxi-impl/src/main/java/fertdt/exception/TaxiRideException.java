package fertdt.exception;

import org.springframework.http.HttpStatus;

public class TaxiRideException extends ApiException {
    public TaxiRideException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
