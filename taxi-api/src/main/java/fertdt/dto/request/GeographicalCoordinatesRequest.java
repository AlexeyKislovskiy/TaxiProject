package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeographicalCoordinatesRequest {
    @NotNull(message = "Longitude cannot be null")
    @Min(value = -180, message = "Longitude cannot be less than {value}")
    @Max(value = 180, message = "Longitude cannot be more than {value}")
    private Double longitude;

    @NotNull(message = "Latitude cannot be null")
    @Min(value = -90, message = "Latitude cannot be less than {value}")
    @Max(value = 90, message = "Latitude cannot be more than {value}")
    private Double latitude;
}
