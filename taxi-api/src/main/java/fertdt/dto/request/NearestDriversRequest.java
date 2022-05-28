package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearestDriversRequest {
    @NotNull(message = "Taxi ride id can not be null")
    private UUID taxiRideId;

    @NotNull(message = "Limit cannot be null")
    @Min(value = 1, message = "Limit cannot be less than {value}")
    private Integer limit;
}
