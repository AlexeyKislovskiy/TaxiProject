package fertdt.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiParkRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "Commission percentage can not be null")
    @Min(value = 0, message = "Commission percentage can not be less than {value}")
    @Max(value = 100, message = "Commission percentage can not be more than {value}")
    private Double commissionPercentage;
}
