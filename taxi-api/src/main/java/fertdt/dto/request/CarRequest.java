package fertdt.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Автомобиль")
public class CarRequest {
    @ApiModelProperty(value = "Регистрационный номер", example = "A123BC")
    @NotBlank(message = "Number can not be blank")
    private String number;

    @ApiModelProperty(value = "Модель", example = "Audi A3")
    @NotBlank(message = "Model can not be blank")
    private String model;

    @ApiModelProperty(value = "Цвет", example = "white")
    @NotBlank(message = "Color can not be blank")
    private String color;

    @ApiModelProperty(value = "Число пассажирских сидений", example = "4")
    @NotNull(message = "Seats number can not be null")
    @Min(value = 1, message = "Seats number can not be less than {value}")
    private Integer seatsNumber;

    @ApiModelProperty(value = "Число детских кресел", example = "1")
    @NotNull(message = "Child seats number can not be null")
    @Min(value = 0, message = "Child seats number can not be less than {value}")
    private Integer childSeatsNumber;

    @ApiModelProperty(value = "Год выпуска", example = "2016")
    @NotNull(message = "Manufacture year can not be null")
    @Min(value = 2008, message = "Car cannot be older than {value}")
    @Max(value = 2022, message = "Manufacture year can not be more than {value}")
    private Integer manufactureYear;

    @ApiModelProperty(value = "ID класса автомобиля")
    @NotNull(message = "Car class id can not be null")
    private UUID carClassId;

    @ApiModelProperty(value = "ID таксопарка")
    @NotNull(message = "Taxi park id can not be null")
    private UUID taxiParkId;
}
