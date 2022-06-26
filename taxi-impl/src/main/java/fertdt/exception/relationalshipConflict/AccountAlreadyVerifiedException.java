package fertdt.exception.relationalshipConflict;

public class AccountAlreadyVerifiedException extends RelationshipConflictException {
    public AccountAlreadyVerifiedException() {
        super();
        this.message = "This account already verified";
    }
}
