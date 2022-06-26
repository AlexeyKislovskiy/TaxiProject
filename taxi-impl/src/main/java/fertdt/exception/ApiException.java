package fertdt.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    protected HttpStatus status;
    protected String message;
}
