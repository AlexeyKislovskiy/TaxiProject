package fertdt.exception.duplicatedName;

public class DuplicatedCarNumberException extends DuplicatedNameException {
    public DuplicatedCarNumberException() {
        super();
        this.message = "Car with this number already exists";
    }
}
