package fertdt.service.jwt;

import fertdt.dto.TokenCoupleDto;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;

public interface JwtTokenService {

    UserResponse getUserInfoByToken(String token);

    TokenCoupleResponse generateTokenCouple(UserResponse accountResponse);

    TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleResponse);
}