package fertdt.service.jwt;

import fertdt.dto.response.UserResponse;
import fertdt.exception.TokenRefreshException;
import fertdt.exception.notFound.UserNotFoundException;
import fertdt.model.RefreshTokenEntity;
import fertdt.model.UserRefreshTokenEntity;
import fertdt.repository.UserRefreshTokenRepository;
import fertdt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseUserRefreshTokenService implements UserRefreshTokenService {

    @Value("${jwt.expiration.refresh.mills}")
    private long expirationRefreshInMills;

    private final UserRefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserRefreshTokenEntity generateRefreshToken(UserResponse accountResponse) {
        return refreshTokenRepository.save(
                UserRefreshTokenEntity.builder()
                        .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                        .account(userRepository
                                .findByUsername(accountResponse.getUsername())
                                .orElseThrow(UserNotFoundException::new))
                        .build()
        );
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiryDate(String refreshToken) {
        return refreshTokenRepository.findById(UUID.fromString(refreshToken)).map(token -> {
            refreshTokenRepository.delete(token);
            if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
                throw new TokenRefreshException(String.valueOf(token.getUuid()), "The refresh token has expired");
            }
            return refreshTokenRepository.save(
                    UserRefreshTokenEntity.builder()
                            .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                            .account(token.getAccount())
                            .build());
        }).orElseThrow(() -> {
            throw new TokenRefreshException(refreshToken, "The token does not exist");
        });
    }
}
