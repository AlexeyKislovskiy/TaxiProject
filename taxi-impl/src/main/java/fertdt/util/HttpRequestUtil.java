package fertdt.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static fertdt.consts.Constants.BEARER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@UtilityClass
public class HttpRequestUtil {

    public String getTokenFromRequest(HttpServletRequest request) {
        return getTokenFromAuthorizationHeader(request.getHeader(AUTHORIZATION));
    }

    public String getTokenFromAuthorizationHeader(String authorizationHeader) {
        return Optional.ofNullable(authorizationHeader)
                .filter(StringUtils::isNotBlank)
                .map(bearer -> StringUtils.removeStart(bearer, BEARER).trim())
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }
}
