package fertdt.exception.relationalshipConflict;

public class TaxiParkOfDriverAndCarDifferentException extends RelationshipConflictException{
    public TaxiParkOfDriverAndCarDifferentException() {
        super();
        this.message = "Cannot add personal car to a taxi park other than the driver's taxi park";
    }
}
