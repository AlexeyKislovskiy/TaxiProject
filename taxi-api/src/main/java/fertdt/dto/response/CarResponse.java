package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private String number;

    private String model;

    private String color;

    private Integer seatsNumber;

    private Integer childSeatsNumber;

    private Integer manufactureYear;

    private String carClass;

    private String taxiPark;

    private String owner;

    private Integer dailyRentalPrice;
}
