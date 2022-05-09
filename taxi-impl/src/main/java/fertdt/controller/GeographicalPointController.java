package fertdt.controller;

import fertdt.api.GeographicalPointApi;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.service.GeographicalPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GeographicalPointController implements GeographicalPointApi {
    private final GeographicalPointService geographicalPointService;

    @Override
    public GeographicalPointResponse getGeographicalPoint(UUID geographicalPointId) {
        return geographicalPointService.getGeographicalPoint(geographicalPointId);
    }
}
