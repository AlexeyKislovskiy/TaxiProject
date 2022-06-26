package fertdt.exception.deletion;

import fertdt.exception.ApiException;
import org.springframework.http.HttpStatus;

public class DeletionException extends ApiException {
    public DeletionException(){
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.message = "Cannot delete because there is nothing to delete";
    }
}
