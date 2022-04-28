package fertdt.service.impl;


import fertdt.dto.enums.Privilege;
import fertdt.dto.enums.Role;
import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.request.UserRequest;
import fertdt.dto.response.UserResponse;
import fertdt.exception.UnauthorizedException;
import fertdt.exception.duplicatedName.DuplicatedUsernameException;
import fertdt.exception.notFound.UserNotFoundException;
import fertdt.model.PrivilegeEntity;
import fertdt.model.RoleEntity;
import fertdt.model.UserEntity;
import fertdt.repository.PrivilegeRepository;
import fertdt.repository.RoleRepository;
import fertdt.repository.UserRepository;
import fertdt.service.UserService;
import fertdt.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UUID createUser(UserExtendedRequest user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(s -> {
            throw new DuplicatedUsernameException();
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.setRoles(Collections.singleton(roleRepository.findByRole(Role.USER)));
        return userRepository.save(userEntity).getUuid();
    }

    @Override
    public void deleteUser(UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return userMapper.toResponse(
                userRepository.findById(userId).orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public UserResponse updateUserById(UUID userId, UserExtendedRequest user) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!userEntity.getUsername().equals(user.getUsername())) {
            userRepository.findByUsername(user.getUsername()).ifPresent(s -> {
                throw new DuplicatedUsernameException();
            });
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toResponse(
                userRepository.save(userMapper.toEntity(userEntity, user))
        );
    }

    @Override
    public Optional<UserResponse> findBySubject(String subject) {
        return userRepository.findByUsername(subject)
                .map(userMapper::toResponse);
    }

    @Override
    public UserResponse login(UserRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getHashPassword()))
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UnauthorizedException("Failed to log in: " + request.getUsername()));
    }
}
