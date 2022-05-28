package fertdt.controller;

import fertdt.api.NavigationApi;
import fertdt.dto.request.NearestDriversRequest;
import fertdt.dto.response.DriverResponse;
import fertdt.service.NavigationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class NavigationController implements NavigationApi {
    private final NavigationService navigationService;

    @Override
    public Set<DriverResponse> findNearestDrivers(NearestDriversRequest nearestDriversRequest) {
        return navigationService.findNearestDrivers(nearestDriversRequest);
    }
}
