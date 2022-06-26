package fertdt.exception.notFound;

public class VehicleCategoryNotFoundException extends NotFoundException {
    public VehicleCategoryNotFoundException() {
        super();
        this.message = "Vehicle category not found";
    }
}
