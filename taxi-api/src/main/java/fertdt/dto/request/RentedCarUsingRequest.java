package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentedCarUsingRequest extends CarUsingRequest {
    @NotNull(message = "Rented for days can not be null")
    @Min(value = 1, message = "Rented for days can not be less than {value}")
    private Integer rentedForDays;
}
