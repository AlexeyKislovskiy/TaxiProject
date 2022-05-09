package fertdt.dto.request;

import fertdt.validation.annotation.WordsNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForwardGeocodingRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 256, message = "Name cannot be more than {max} characters")
    @WordsNumber(max = 20, message = "Name must consist of at most {max} words")
    private String name;

    @NotNull(message = "Limit cannot be null")
    @Min(value = 1, message = "Limit cannot be less than {value}")
    @Max(value = 10, message = "Limit cannot be more than {value}")
    private Integer limit;

    private GeographicalCoordinatesRequest proximityCoordinates;
}
