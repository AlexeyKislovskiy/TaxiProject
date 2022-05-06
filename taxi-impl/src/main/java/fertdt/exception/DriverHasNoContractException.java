package fertdt.exception;

import org.springframework.http.HttpStatus;

public class DriverHasNoContractException extends ApiException {
    public DriverHasNoContractException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
