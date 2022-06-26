package fertdt.exception;

import org.springframework.http.HttpStatus;

public class DriverAtWorkException extends ApiException{
    public DriverAtWorkException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
