package fertdt.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {

    public UnauthorizedException(String message) {
        this.status = HttpStatus.UNAUTHORIZED;
        this.message = message;
    }
}