package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReverseGeocodingRequest {
    @NotNull(message = "Geographical coordinates can not be null")
    private GeographicalCoordinatesRequest geographicalCoordinates;
}
