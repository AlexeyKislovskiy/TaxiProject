package fertdt.service.impl;

import fertdt.dto.response.DriverResponse;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.relationalshipConflict.UserAlreadyHasDriverAccountException;
import fertdt.repository.DriverRepository;
import fertdt.service.DriverService;
import fertdt.util.mapper.DriverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final UserServiceImpl userService;

    @Override
    public void createDriverAccount(UUID userId) {
        userService.getUserById(userId);
        driverRepository.findById(userId).ifPresent(s -> {
            throw new UserAlreadyHasDriverAccountException();
        });
        driverRepository.createDriverAccount(userId);
    }

    @Override
    public DriverResponse getDriverById(UUID driverId) {
        return driverMapper.toResponse(
                driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new)
        );
    }
}
