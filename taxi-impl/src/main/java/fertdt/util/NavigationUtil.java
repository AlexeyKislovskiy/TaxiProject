package fertdt.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NavigationUtil {
    public static double distance(double latitude1, double longitude1,
                                  double latitude2, double longitude2) {
        double a = (latitude1 - latitude2) * distancePerLatitude(latitude1);
        double b = (longitude1 - longitude2) * distancePerLongitude(latitude1);
        return Math.sqrt(a * a + b * b);
    }

    private static double distancePerLongitude(double latitude) {
        return 0.0003121092 * Math.pow(latitude, 4)
                + 0.0101182384 * Math.pow(latitude, 3)
                - 17.2385140059 * latitude * latitude
                + 5.5485277537 * latitude + 111301.967182595;
    }

    private static double distancePerLatitude(double latitude) {
        return -0.000000487305676 * Math.pow(latitude, 4)
                - 0.0033668574 * Math.pow(latitude, 3)
                + 0.4601181791 * latitude * latitude
                - 1.4558127346 * latitude + 110579.25662316;
    }
}
