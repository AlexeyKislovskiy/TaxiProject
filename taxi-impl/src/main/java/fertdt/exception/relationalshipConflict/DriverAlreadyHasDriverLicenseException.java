package fertdt.exception.relationalshipConflict;

public class DriverAlreadyHasDriverLicenseException extends RelationshipConflictException{
    public DriverAlreadyHasDriverLicenseException() {
        super();
        this.message = "This driver already has driver license";
    }
}
