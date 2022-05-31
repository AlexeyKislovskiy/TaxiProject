package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Водитель")
public class DriverResponse extends UserResponse {
    @ApiModelProperty(value = "Рейтинг в качестве водителя", example = "4.8")
    private Double ratingAsDriver;

    @ApiModelProperty(value = "Паспорта")
    private Set<PassportResponse> passports;

    @ApiModelProperty(value = "Водительские права")
    private Set<DriverLicenseResponse> driverLicenses;

    @ApiModelProperty(value = "Таксопарк")
    private TaxiParkResponse taxiPark;

    @ApiModelProperty(value = "Статус")
    private DriverStatusResponse driverStatus;
}
