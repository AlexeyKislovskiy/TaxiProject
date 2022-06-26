package fertdt.exception.relationalshipConflict;

import fertdt.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CarNotAppropriateException extends ApiException {
    public CarNotAppropriateException() {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = "This car does not fit the call";
    }
}
