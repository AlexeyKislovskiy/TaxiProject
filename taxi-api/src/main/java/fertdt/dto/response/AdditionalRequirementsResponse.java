package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalRequirementsResponse {
    private Integer seatsNumber;

    private Integer childSeatsNumber;

    private String commentToDriver;
}
