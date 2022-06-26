package fertdt.exception.relationalshipConflict;

public class DriverAlreadySignedContractException extends RelationshipConflictException{
    public DriverAlreadySignedContractException(){
        super();
        this.message = "Driver has already signed a contract with taxi park";
    }
}
