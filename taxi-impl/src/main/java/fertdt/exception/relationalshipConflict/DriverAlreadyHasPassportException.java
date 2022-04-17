package fertdt.exception.relationalshipConflict;

public class DriverAlreadyHasPassportException extends RelationshipConflictException{
    public DriverAlreadyHasPassportException() {
        super();
        this.message = "This driver already has passport";
    }
}
