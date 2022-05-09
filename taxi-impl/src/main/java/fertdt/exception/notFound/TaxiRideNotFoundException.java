package fertdt.exception.notFound;

public class TaxiRideNotFoundException extends NotFoundException {
    public TaxiRideNotFoundException() {
        super();
        this.message = "Taxi ride not found";
    }
}
