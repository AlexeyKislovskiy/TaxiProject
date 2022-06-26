package fertdt.exception;

import org.springframework.http.HttpStatus;

public class ChatException extends ApiException {
    public ChatException(String message) {
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = message;
    }
}
