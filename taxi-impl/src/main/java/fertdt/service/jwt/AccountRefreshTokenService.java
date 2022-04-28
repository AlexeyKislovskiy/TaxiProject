package fertdt.service.jwt;

import fertdt.dto.response.UserResponse;
import fertdt.model.RefreshTokenEntity;

public interface AccountRefreshTokenService {

    RefreshTokenEntity generateRefreshToken(UserResponse accountResponse);

    RefreshTokenEntity verifyRefreshTokenExpiryDate(String refreshToken);
}
