package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForCarUsingResponse {
    private Integer rentedForDays;

    private Integer cost;

    private String paymentStatus;
}
