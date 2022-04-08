package fertdt.service.impl;


import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.response.UserResponse;
import fertdt.exception.duplicatedName.DuplicatedUsernameException;
import fertdt.exception.notFound.UserNotFoundException;
import fertdt.model.UserEntity;
import fertdt.repository.UserRepository;
import fertdt.service.UserService;
import fertdt.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UUID createUser(UserExtendedRequest user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(s -> {
            throw new DuplicatedUsernameException();
        });
        return userRepository.save(userMapper.toEntity(user)).getUuid();
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
        return userMapper.toResponse(
                userRepository.save(userMapper.toEntity(userEntity, user))
        );
    }
}
