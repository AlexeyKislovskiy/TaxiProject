package fertdt.exception.duplicatedName;

public class DuplicatedTaxiParkNameException extends DuplicatedNameException {
    public DuplicatedTaxiParkNameException() {
        super();
        this.message = "Taxi park with this name already exists";
    }
}
