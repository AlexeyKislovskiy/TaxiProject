package fertdt.dto;

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
@ApiModel(description = "Предварительный расчет деталей поездки")
public class PredictedTripDto {
    @ApiModelProperty(value = "Длина пути в километрах", example = "14")
    private Integer totalDistance;

    @ApiModelProperty(value = "Предполагаемое время ожидания ближайшего водителя в минутах или null, если не найдено" +
            " ни одного водителя с подходящими для заказа параметрами", example = "5")
    private Integer predictedDriverWaitingTime;

    @ApiModelProperty(value = "Предполагаемое время пути в минутах", example = "26")
    private Integer predictedTripTime;

    @ApiModelProperty(value = "Предварительная цена поездки в рублях. Может увеличиться из-за штрафа за долгое ожидание", example = "320")
    private Integer tripPrice;
}
