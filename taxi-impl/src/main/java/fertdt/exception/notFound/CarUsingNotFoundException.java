package fertdt.exception.notFound;

public class CarUsingNotFoundException extends NotFoundException{
    public CarUsingNotFoundException() {
        super();
        this.message = "Car using not found";
    }
}
