package fertdt.exception;

import org.springframework.http.HttpStatus;

public class MapboxApiException extends ApiException {
    public MapboxApiException(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }
}
