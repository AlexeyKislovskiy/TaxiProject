package fertdt.consts;

public interface Constants {

    String ROLE = "ROLE";
    String PRIVILEGE = "PRIVILEGE";
    String BEARER = "Bearer";
    String REFRESH_TOKEN = "Refresh-token";
    String ACCESS_TOKEN_EXPIRATION_TIME = "Access-token-expiration-time";

    String FORWARD_GEOCODING_URI = "https://api.mapbox.com/geocoding/v5/mapbox.places/{name}.json?limit={limit}" +
            "&access_token={access_token}";
    String FORWARD_GEOCODING_URI_WITH_PROXIMITY = FORWARD_GEOCODING_URI + "&proximity={proximity}";
    String REVERSE_GEOCODING_URI = "https://api.mapbox.com/geocoding/v5/mapbox.places/{longitude},{latitude}.json?" +
            "access_token={access_token}";
    String NAME_PARAMETER = "name";
    String LIMIT_PARAMETER = "limit";
    String PROXIMITY_PARAMETER = "proximity";
    String ACCESS_TOKEN_PARAMETER = "access_token";
    String LONGITUDE_PARAMETER = "longitude";
    String LATITUDE_PARAMETER = "latitude";
}
