package fertdt.exception.notFound;

public class PassportNotFoundException extends NotFoundException {
    public PassportNotFoundException() {
        super();
        this.message = "Passport not found";
    }
}
