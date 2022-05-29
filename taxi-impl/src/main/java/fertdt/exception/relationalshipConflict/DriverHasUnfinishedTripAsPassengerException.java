package fertdt.exception.relationalshipConflict;

public class DriverHasUnfinishedTripAsPassengerException extends RelationshipConflictException {
    public DriverHasUnfinishedTripAsPassengerException() {
        super();
        this.message = "Driver has unfinished trip as passenger, cannot start work";
    }
}
