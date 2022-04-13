package fertdt.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    @NotBlank(message = "Number can not be blank")
    private String number;

    @NotBlank(message = "Model can not be blank")
    private String model;

    @NotBlank(message = "Color can not be blank")
    private String color;

    @NotNull(message = "Seats number can not be null")
    @Min(value = 1, message = "Seats number can not be less than {value}")
    private Integer seatsNumber;

    @NotNull(message = "Child seats number can not be null")
    @Min(value = 0, message = "Child seats number can not be less than {value}")
    private Integer childSeatsNumber;

    @NotNull(message = "Manufacture year can not be null")
    @Min(value = 2008, message = "Car cannot be older than {value}")
    @Max(value = 2022, message = "Manufacture year can not be more than {value}")
    private Integer manufactureYear;

    @NotNull(message = "Car class id can not be null")
    private UUID carClassId;

    @NotNull(message = "Taxi park id can not be null")
    private UUID taxiParkId;
}
