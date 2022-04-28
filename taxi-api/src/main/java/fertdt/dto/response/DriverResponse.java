package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse extends UserResponse {
    private Double ratingAsDriver;

    private Set<PassportResponse> passports;

    private Set<DriverLicenseResponse> driverLicenses;
}
