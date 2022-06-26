package fertdt.exception;

import org.springframework.http.HttpStatus;

public class JsonException extends ApiException {
    public JsonException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
