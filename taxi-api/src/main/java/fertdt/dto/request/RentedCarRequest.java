package fertdt.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentedCarRequest extends CarRequest {
    @NotNull(message = "Daily rental price can not be null")
    @Min(value = 0, message = "Daily rental price can not be less than {value}")
    private Integer dailyRentalPrice;
}
