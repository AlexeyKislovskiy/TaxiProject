package fertdt.provider;

import fertdt.dto.response.UserResponse;
import fertdt.model.RefreshTokenEntity;

import java.util.List;

public interface JwtRefreshTokenProvider {
    String generateRefreshToken(UserResponse accountResponse);

    RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken, List<String> roles);
}
