package fertdt.exception.notFound;

public class DriverLicenseNotFoundException extends NotFoundException{
    public DriverLicenseNotFoundException() {
        super();
        this.message = "Driver license not found";
    }
}
