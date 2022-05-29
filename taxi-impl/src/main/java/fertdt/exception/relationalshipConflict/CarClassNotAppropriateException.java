package fertdt.exception.relationalshipConflict;

public class CarClassNotAppropriateException extends RelationshipConflictException {
    public CarClassNotAppropriateException() {
        super();
        this.message = "The class of the car from the call and this one are different";
    }
}
