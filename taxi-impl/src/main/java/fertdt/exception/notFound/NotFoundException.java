package fertdt.exception.notFound;

import fertdt.exception.ApiException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException() {
        this.status = HttpStatus.NOT_FOUND;
        this.message = "Object not found";
    }
}
