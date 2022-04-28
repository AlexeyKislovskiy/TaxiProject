package fertdt.service;

import fertdt.dto.response.UserResponse;

import java.util.Optional;

public interface AccountService {

    Optional<UserResponse> findBySubject(String subject);
}