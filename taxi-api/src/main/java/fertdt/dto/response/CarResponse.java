package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Автомобиль")
public class CarResponse {
    @ApiModelProperty(value = "Регистрационный номер", example = "A123BC")
    private String number;

    @ApiModelProperty(value = "Модель", example = "Audi A3")
    private String model;

    @ApiModelProperty(value = "Цвет", example = "white")
    private String color;

    @ApiModelProperty(value = "Число пассажирских сидений", example = "4")
    private Integer seatsNumber;

    @ApiModelProperty(value = "Число детских кресел", example = "1")
    private Integer childSeatsNumber;

    @ApiModelProperty(value = "Год выпуска", example = "2016")
    private Integer manufactureYear;

    @ApiModelProperty(value = "Класс автомобиля")
    private CarClassResponse carClass;

    @ApiModelProperty(value = "Таксопарк")
    private TaxiParkResponse taxiPark;

    @ApiModelProperty(value = "Юзернейм владельца", example = "fertdt")
    private String owner;

    @ApiModelProperty(value = "Ежедневная арендная плата в рублях", example = "1000")
    private Integer dailyRentalPrice;
}
