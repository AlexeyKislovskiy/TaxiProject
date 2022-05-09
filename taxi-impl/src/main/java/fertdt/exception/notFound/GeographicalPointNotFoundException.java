package fertdt.exception.notFound;

public class GeographicalPointNotFoundException extends NotFoundException {
    public GeographicalPointNotFoundException() {
        super();
        this.message = "Geographical point not found";
    }
}
