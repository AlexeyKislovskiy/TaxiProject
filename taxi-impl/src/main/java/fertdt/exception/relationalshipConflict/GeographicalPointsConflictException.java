package fertdt.exception.relationalshipConflict;

public class GeographicalPointsConflictException extends RelationshipConflictException {
    public GeographicalPointsConflictException() {
        super();
        this.message = "Geographical points of the order cannot be same";
    }
}
