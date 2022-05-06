package fertdt.exception.relationalshipConflict;

public class DriverAlreadyUseCarException extends RelationshipConflictException {
    public DriverAlreadyUseCarException() {
        super();
        this.message = "Driver already use car";
    }
}
