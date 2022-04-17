package fertdt.exception.duplicatedName;

public class DuplicatedPassportSeriesAndNumberException extends DuplicatedNameException {
    public DuplicatedPassportSeriesAndNumberException() {
        super();
        this.message = "Passport with this series and number already exists";
    }
}
