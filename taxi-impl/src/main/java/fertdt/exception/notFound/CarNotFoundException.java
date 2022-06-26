package fertdt.exception.notFound;

public class CarNotFoundException extends NotFoundException {
    public CarNotFoundException() {
        super();
        this.message = "Car not found";
    }
}
