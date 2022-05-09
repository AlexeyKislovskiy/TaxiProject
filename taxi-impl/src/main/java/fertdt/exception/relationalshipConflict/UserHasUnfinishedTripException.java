package fertdt.exception.relationalshipConflict;

public class UserHasUnfinishedTripException extends RelationshipConflictException {
    public UserHasUnfinishedTripException() {
        super();
        this.message = "User has an unfinished trip, it is impossible to call a new taxi";
    }
}
