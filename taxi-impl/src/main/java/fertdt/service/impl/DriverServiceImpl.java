package fertdt.service.impl;

import fertdt.dto.response.DriverResponse;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.notFound.UserNotFoundException;
import fertdt.exception.relationalshipConflict.UserAlreadyHasDriverAccountException;
import fertdt.model.DriverEntity;
import fertdt.repository.DriverRepository;
import fertdt.repository.UserRepository;
import fertdt.service.DriverService;
import fertdt.util.mapper.DriverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public UUID createDriverAccount(UUID userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        driverRepository.findById(userId).ifPresent(s -> {
            throw new UserAlreadyHasDriverAccountException();
        });
        return driverRepository.save(DriverEntity.builder().uuid(userId).build()).getUuid();
    }

    @Override
    public DriverResponse getDriverById(UUID driverId) {
        return driverMapper.toResponse(
                driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new)
        );
    }
}
