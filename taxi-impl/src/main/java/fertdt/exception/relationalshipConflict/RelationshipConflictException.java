package fertdt.exception.relationalshipConflict;

import fertdt.exception.ApiException;
import org.springframework.http.HttpStatus;

public class RelationshipConflictException extends ApiException {
    public RelationshipConflictException() {
        this.status = HttpStatus.CONFLICT;
        this.message = "Relationship conflict";
    }
}
