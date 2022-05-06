package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableCarsResponse {
    private Set<CarResponse> personalCars;
    private Set<CarResponse> rentedCars;
}
