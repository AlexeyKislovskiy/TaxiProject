package fertdt.exception.duplicatedName;

public class DuplicatedUsernameException extends DuplicatedNameException {
    public DuplicatedUsernameException() {
        super();
        this.message = "User with this username already exists";
    }
}
