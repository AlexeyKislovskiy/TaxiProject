package fertdt.security.userdetails;

import fertdt.dto.response.PrivilegeResponse;
import fertdt.dto.response.RoleResponse;
import fertdt.dto.response.UserResponse;
import fertdt.exception.AuthenticationHeaderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) {
        return loadUserDetails((UserResponse) preAuthenticatedAuthenticationToken.getPrincipal(), String.valueOf(preAuthenticatedAuthenticationToken.getCredentials()));
    }

    private UserDetails loadUserDetails(UserResponse userResponse, String token) {
        try {
            return Optional.ofNullable(userResponse)
                    .map(account -> {
                        List<SimpleGrantedAuthority> authorities = getAuthorities(account.getRoles());
                        return UserAccount.builder()
                                .id(account.getId())
                                .username(account.getUsername())
                                .fullName(account.getFirstName().concat(StringUtils.SPACE).concat(account.getLastName()))
                                .createDate(null)
                                .authorities(authorities)
                                .isAccountNonExpired(true)
                                .isCredentialsNonExpired(true)
                                .isAccountNonLocked(true)
                                .isEnabled(true)
                                .token(token)
                                .build();
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token " + token));
        } catch (Exception exception) {
            throw new AuthenticationHeaderException(exception.getMessage());
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(Set<RoleResponse> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.addAll(role.getPrivileges().stream()
                    .map(PrivilegeResponse::getPrivilege)
                    .map(privilege -> "PRIVILEGE_" + privilege)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));

            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });

        return authorities;
    }
}