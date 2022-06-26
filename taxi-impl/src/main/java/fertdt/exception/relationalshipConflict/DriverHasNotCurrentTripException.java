package fertdt.exception.relationalshipConflict;

public class DriverHasNotCurrentTripException extends RelationshipConflictException{
    public DriverHasNotCurrentTripException(String message) {
        super();
        this.message = message;
    }
}
