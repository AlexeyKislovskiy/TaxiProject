package fertdt.exception.notFound;

public class TaxiParkNotFoundException extends NotFoundException {
    public TaxiParkNotFoundException() {
        super();
        this.message = "Taxi park not found";
    }
}
