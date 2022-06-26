package fertdt.exception.duplicatedName;

public class DuplicatedDriverLicenseIdNumberException extends DuplicatedNameException {
    public DuplicatedDriverLicenseIdNumberException() {
        super();
        this.message = "Driver license with this id number already exists";
    }
}
