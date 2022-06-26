package fertdt.exception.relationalshipConflict;

public class UserAlreadyHasDriverAccountException extends RelationshipConflictException{
    public UserAlreadyHasDriverAccountException() {
        super();
        this.message = "This user already has driver account";
    }
}
