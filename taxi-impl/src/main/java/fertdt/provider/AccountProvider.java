package fertdt.provider;

import fertdt.service.AccountService;
import fertdt.service.jwt.AccountRefreshTokenService;

import java.util.List;

public interface AccountProvider {

    AccountService getAccountService(List<String> roles);

    AccountRefreshTokenService getAccountRefreshTokenService(List<String> roles);
}
