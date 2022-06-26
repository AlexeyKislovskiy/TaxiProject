package fertdt.exception.deletion;

public class DriverBreakContractException extends DeletionException {
    public DriverBreakContractException(){
        super();
        this.message = "Driver already has no contract with the taxi park";
    }
}
