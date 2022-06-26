package fertdt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DriverStatus {
    NO_REQUIRED_DOCUMENTS("Driver did not add the required documents (passport and driver's license)"),
    DOCUMENTS_NOT_VERIFIED("Driver added the documents, but they have not been verified yet"),
    NO_CONTRACT_WITH_TAXI_PARK("Documents have been verified, but the driver has not signed a contract with the taxi park"),
    NOT_AT_WORK("Driver has signed a contract, is not at work at the moment"),
    AT_WORK("Driver is at work at the moment");

    private final String description;
}
