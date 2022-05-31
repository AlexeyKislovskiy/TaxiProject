package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Параметры заказа такси")
public class UpcomingTaxiCallRequest {
    @ApiModelProperty(value = "ID пассажира")
    @NotNull(message = "Passenger id can not be null")
    private UUID passengerId;

    @ApiModelProperty(value = "Начальный пункт")
    @NotNull(message = "Starting point can not be null")
    private GeographicalCoordinatesRequest startingPoint;

    @ApiModelProperty(value = "Список промежуточных пунктов остановки")
    @Size(max = 10, message = "Number of intermediate points cannot be more than {max}")
    private List<GeographicalCoordinatesRequest> intermediatePoints;

    @ApiModelProperty(value = "Важен ли порядок промежуточных точек остановки. Если да, то между промежуточными точками " +
            "будет построен маршрут в порядке задания, если нет, то между ними будет построен оптимальный по времени " +
            "маршрут", example = "false")
    private Boolean intermediatePointsOrderImportant;

    @ApiModelProperty(value = "Конечный пункт")
    @NotNull(message = "Destination can not be null")
    private GeographicalCoordinatesRequest destination;

    @ApiModelProperty(value = "ID класса автомобиля")
    @NotNull(message = "Car class id can not be null")
    private UUID carClassId;

    @ApiModelProperty(value = "ID способа платежа")
    @NotNull(message = "Payment method id can not be null")
    private UUID paymentMethodId;

    @ApiModelProperty(value = "Дополнительные требования")
    private AdditionalRequirementsRequest additionalRequirements;
}
