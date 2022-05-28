package fertdt.exception.relationalshipConflict;

public class DriverAndPassengerSameException extends RelationshipConflictException {
    public DriverAndPassengerSameException() {
        super();
        this.message = "Driver and passenger cannot be same";
    }
}
