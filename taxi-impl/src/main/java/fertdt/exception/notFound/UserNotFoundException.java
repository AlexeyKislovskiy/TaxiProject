package fertdt.exception.notFound;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super();
        this.message = "User not found";
    }
}
