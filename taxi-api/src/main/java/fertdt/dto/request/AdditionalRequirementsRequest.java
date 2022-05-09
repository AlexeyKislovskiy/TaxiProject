package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalRequirementsRequest {
    @Min(value = 1, message = "Seats number can not be less than {value}")
    private Integer seatsNumber;

    @Min(value = 0, message = "Child seats number can not be less than {value}")
    private Integer childSeatsNumber;

    private String commentToDriver;
}
