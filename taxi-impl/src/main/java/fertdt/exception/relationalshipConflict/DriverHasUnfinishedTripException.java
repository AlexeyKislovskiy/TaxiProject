package fertdt.exception.relationalshipConflict;

public class DriverHasUnfinishedTripException extends RelationshipConflictException {
    public DriverHasUnfinishedTripException() {
        super();
        this.message = "Driver has an unfinished trip, it is impossible to take a new order";
    }
}
