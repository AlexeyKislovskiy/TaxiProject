package fertdt.exception.notFound;

public class DriverNotFoundException extends NotFoundException {
    public DriverNotFoundException() {
        super();
        this.message = "Driver not found";
    }
}
