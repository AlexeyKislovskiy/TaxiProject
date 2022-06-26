package fertdt.provider;

import fertdt.service.AccountService;
import fertdt.service.UserService;
import fertdt.service.jwt.AccountRefreshTokenService;
import fertdt.service.jwt.UserRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountProviderImpl implements AccountProvider {

    private final UserService userService;
    private final UserRefreshTokenService userRefreshTokenService;

    @Override
    public AccountService getAccountService(List<String> roles) {
        return userService;
    }

    @Override
    public AccountRefreshTokenService getAccountRefreshTokenService(List<String> roles) {
        return userRefreshTokenService;
    }
}
