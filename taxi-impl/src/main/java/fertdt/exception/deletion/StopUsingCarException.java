package fertdt.exception.deletion;

public class StopUsingCarException extends DeletionException {
    public StopUsingCarException() {
        super();
        this.message = "Driver already doesn't use car";
    }
}
