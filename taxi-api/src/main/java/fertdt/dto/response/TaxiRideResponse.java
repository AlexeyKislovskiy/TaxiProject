package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Поездка")
public class TaxiRideResponse {
    @ApiModelProperty(value = "Пассажир")
    private UserResponse passenger;

    @ApiModelProperty(value = "Начальный пункт")
    private GeographicalPointResponse startingPoint;

    @ApiModelProperty(value = "Список промежуточных пунктов остановки")
    private List<GeographicalPointResponse> intermediatePoints;

    @ApiModelProperty(value = "Важен ли порядок промежуточных точек остановки. Если да, то между промежуточными точками " +
            "будет построен маршрут в порядке задания, если нет, то между ними будет построен оптимальный по времени " +
            "маршрут", example = "false")
    private Boolean intermediatePointsOrderImportant;

    @ApiModelProperty(value = "Конечный пункт")
    private GeographicalPointResponse destination;

    @ApiModelProperty(value = "Класс автомобиля")
    private CarClassResponse carClass;

    @ApiModelProperty(value = "Способ платежа")
    private PaymentMethodResponse paymentMethod;

    @ApiModelProperty(value = "Дополнительные требования")
    private AdditionalRequirementsResponse additionalRequirements;

    @ApiModelProperty(value = "Статус поездки", example = "TRIP_IS_OVER")
    private String taxiRideStatus;

    @ApiModelProperty(value = "Водитель")
    private DriverResponse driver;

    @ApiModelProperty(value = "Автомобиль")
    private CarResponse car;

    @ApiModelProperty(value = "Длина пути в километрах", example = "14")
    private Integer totalDistance;

    @ApiModelProperty(value = "Время прибытия к пассажиру", example = "2022-05-29 13:02:12.035084")
    private String arrivingTime;

    @ApiModelProperty(value = "Время начала поездки", example = "2022-05-29 13:02:28.300363")
    private String tripStartTime;

    @ApiModelProperty(value = "Время завершения поездки", example = "2022-05-29 13:28:41.730275")
    private String tripFinishTime;

    @ApiModelProperty(value = "Предполагаемое время пути в минутах", example = "26")
    private Integer predictedTripTime;

    @ApiModelProperty(value = "Итоговая цена поездки в рублях", example = "320")
    private Integer tripPrice;

    @ApiModelProperty(value = "Сумма чаевых в рублях", example = "15")
    private Integer tips;

    @ApiModelProperty(value = "Оценка от пассажира водителю")
    private List<RatingResponse> ratingToDriver;

    @ApiModelProperty(value = "Оценка от водителя пассажиру")
    private List<RatingResponse> ratingToPassenger;

    @ApiModelProperty(value = "Список жалоб от пассажира водителю")
    private List<ComplaintResponse> complaintsToDriver;

    @ApiModelProperty(value = "Список жалоб от водителя пассажиру")
    private List<ComplaintResponse> complaintsToPassenger;
}
