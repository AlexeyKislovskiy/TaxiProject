package fertdt.controller;

import fertdt.api.JwtTokenApi;
import fertdt.dto.TokenCoupleDto;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;
import fertdt.service.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtTokenController implements JwtTokenApi {

    private final JwtTokenService jwtTokenService;

    @Override
    public UserResponse userInfoByToken(String token) {
        return jwtTokenService.getUserInfoByToken(token);
    }

    @Override
    public TokenCoupleResponse updateTokens(TokenCoupleDto tokenCoupleDto) {
        return jwtTokenService.refreshAccessToken(tokenCoupleDto);
    }
}