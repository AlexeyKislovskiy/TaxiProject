package fertdt.provider;

import fertdt.dto.response.RoleResponse;
import fertdt.dto.response.UserResponse;
import fertdt.model.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtRefreshTokenProviderImpl implements JwtRefreshTokenProvider {

    private final AccountProvider accountProvider;

    @Override
    public String generateRefreshToken(UserResponse accountResponse) {
        List<String> roles = accountResponse.getRoles().stream()
                .map(RoleResponse::getRole).collect(Collectors.toList());
        return String.valueOf(accountProvider.getAccountRefreshTokenService(roles)
                .generateRefreshToken(accountResponse).getUuid());
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken, List<String> roles) {
        return accountProvider.getAccountRefreshTokenService(roles).verifyRefreshTokenExpiryDate(refreshToken);
    }
}
