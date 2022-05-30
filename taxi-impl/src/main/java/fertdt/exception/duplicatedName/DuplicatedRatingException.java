package fertdt.exception.duplicatedName;

public class DuplicatedRatingException extends DuplicatedNameException {
    public DuplicatedRatingException() {
        super();
        this.message = "This user already put rating in this taxi ride";
    }
}
