package fertdt.exception.notFound;

public class CarClassNotFoundException extends NotFoundException {
    public CarClassNotFoundException() {
        super();
        this.message = "Car class not found";
    }
}
