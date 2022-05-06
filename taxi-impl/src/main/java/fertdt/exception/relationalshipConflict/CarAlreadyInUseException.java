package fertdt.exception.relationalshipConflict;

public class CarAlreadyInUseException extends RelationshipConflictException {
    public CarAlreadyInUseException() {
        super();
        this.message = "Car already in use";
    }
}
