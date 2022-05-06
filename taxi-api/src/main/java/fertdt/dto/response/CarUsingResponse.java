package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarUsingResponse {
    private CarResponse car;

    private DriverResponse driver;

    private String fromDate;

    private String toDate;

    private PaymentForCarUsingResponse payment;
}
