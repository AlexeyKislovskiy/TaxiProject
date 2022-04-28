package fertdt.service.jwt;

import fertdt.dto.TokenCoupleDto;
import fertdt.dto.response.RoleResponse;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;
import fertdt.model.RefreshTokenEntity;
import fertdt.provider.JwtAccessTokenProvider;
import fertdt.provider.JwtRefreshTokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static fertdt.consts.Constants.BEARER;
import static fertdt.consts.Constants.ROLE;


@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;

    @Override
    public UserResponse getUserInfoByToken(String token) {
        return jwtAccessTokenProvider.userInfoByToken(token);
    }

    @Override
    public TokenCoupleResponse generateTokenCouple(UserResponse accountResponse) {
        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                accountResponse.getUsername(),
                Collections.singletonMap(ROLE, accountResponse
                        .getRoles().stream()
                        .map(RoleResponse::getRole)
                        .collect(Collectors.toList()))
        );
        String refreshToken = jwtRefreshTokenProvider.generateRefreshToken(accountResponse);
        return TokenCoupleResponse.builder()
                .accessToken(BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .refreshToken(refreshToken)
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto) {
        List<String> roles = jwtAccessTokenProvider.getRolesFromAccessToken(tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY));
        RefreshTokenEntity verifiedRefreshToken = jwtRefreshTokenProvider.verifyRefreshTokenExpiration(
                tokenCoupleDto.getRefreshToken(), roles
        );

        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                jwtAccessTokenProvider.getSubjectFromAccessToken(tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY)),
                Collections.singletonMap(ROLE, roles));
        return TokenCoupleResponse.builder()
                .refreshToken(String.valueOf(verifiedRefreshToken.getUuid()))
                .accessToken(BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .accessTokenExpirationDate(jwtAccessTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }
}