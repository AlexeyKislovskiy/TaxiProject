package fertdt.exception;

import org.springframework.http.HttpStatus;

public class TokenRefreshException extends ApiException {

    public TokenRefreshException(String token, String message) {
        this.status = HttpStatus.UNAUTHORIZED;
        this.message = String.format("Failed for [%s]: %s", token, message);
    }
}