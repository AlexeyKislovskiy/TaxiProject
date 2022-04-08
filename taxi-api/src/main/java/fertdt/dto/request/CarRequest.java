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
    @NotBlank
    private String number;

    @NotBlank
    private String model;

    @NotBlank
    private String color;

    @NotNull
    @Min(1)
    private Integer seatsNumber;

    @NotNull
    @Min(0)
    private Integer childSeatsNumber;

    @NotNull
    @Min(2008)
    @Max(2022)
    private Integer manufactureYear;

    @NotNull
    private UUID carClassId;

    @NotNull
    private UUID taxiParkId;
}
