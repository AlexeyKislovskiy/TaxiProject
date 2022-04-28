package fertdt.provider;

import fertdt.dto.response.UserResponse;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JwtAccessTokenProvider {
    String generateAccessToken(String subject, Map<String, Object> data);

    boolean validateAccessToken(String accessToken, String subject);

    UserResponse userInfoByToken(String token);

    Claims parseAccessToken(String accessToken);

    List<String> getRolesFromAccessToken(String accessToken);

    Date getExpirationDateFromAccessToken(String accessToken);

    String getSubjectFromAccessToken(String accessToken);
}