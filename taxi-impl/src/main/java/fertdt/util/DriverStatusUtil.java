package fertdt.util;

import fertdt.model.DriverEntity;
import fertdt.model.DriverStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DriverStatusUtil {
    public static boolean canWork(DriverEntity driver) {
        return driver.getDriverStatus().equals(DriverStatus.NOT_AT_WORK) || driver.getDriverStatus().equals(DriverStatus.AT_WORK);
    }

    public static boolean accountVerified(DriverEntity driver) {
        return !driver.getDriverStatus().equals(DriverStatus.NO_REQUIRED_DOCUMENTS) && !driver.getDriverStatus().equals(DriverStatus.DOCUMENTS_NOT_VERIFIED);
    }
}
