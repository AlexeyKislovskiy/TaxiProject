package fertdt.util;

import fertdt.model.CarEntity;
import fertdt.model.CarUsingEntity;
import fertdt.model.DriverEntity;
import lombok.experimental.UtilityClass;

import java.time.Instant;

@UtilityClass
public class CarUsingUtil {
    public static CarUsingEntity getCurrentCarUsing(DriverEntity driver) {
        for (CarUsingEntity carUsing : driver.getCarUsing()) {
            if (carUsing.getToDate() == null || carUsing.getToDate().isAfter(Instant.now())) return carUsing;
        }
        return null;
    }

    public static CarEntity getCurrentCar(DriverEntity driver) {
        CarUsingEntity currentCarUsing = getCurrentCarUsing(driver);
        if (currentCarUsing == null) return null;
        else return currentCarUsing.getCar();
    }

    public static DriverEntity getCurrentDriver(CarEntity car) {
        for (CarUsingEntity carUsing : car.getCarUsing()) {
            if (carUsing.getToDate() == null || carUsing.getToDate().isAfter(Instant.now()))
                return carUsing.getDriver();
        }
        return null;
    }
}
