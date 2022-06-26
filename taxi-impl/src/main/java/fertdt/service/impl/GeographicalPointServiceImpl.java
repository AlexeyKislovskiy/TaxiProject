package fertdt.service.impl;

import fertdt.dto.request.GeographicalCoordinatesRequest;
import fertdt.dto.request.ReverseGeocodingRequest;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.exception.notFound.GeographicalPointNotFoundException;
import fertdt.model.GeographicalPointEntity;
import fertdt.repository.GeographicalPointRepository;
import fertdt.service.GeocodingService;
import fertdt.service.GeographicalPointService;
import fertdt.util.mapper.GeographicalPointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GeographicalPointServiceImpl implements GeographicalPointService {
    private final GeographicalPointRepository geographicalPointRepository;
    private final GeographicalPointMapper geographicalPointMapper;
    private final GeocodingService geocodingService;

    @Override
    public GeographicalPointEntity getGeographicalPointFromCoordinates(GeographicalCoordinatesRequest geographicalCoordinatesRequest) {
        List<GeographicalPointResponse> geographicalPoints = geocodingService.reverseGeocoding
                (ReverseGeocodingRequest.builder().geographicalCoordinates(geographicalCoordinatesRequest).build());
        Optional<GeographicalPointEntity> optionalPoint = geographicalPointRepository.findByName(geographicalPoints.get(0).getName());
        if (optionalPoint.isPresent()) return optionalPoint.get();
        GeographicalPointEntity geographicalPoint = geographicalPointMapper.toEntity(geographicalPoints.get(0));
        return geographicalPointRepository.save(geographicalPoint);
    }

    @Override
    public GeographicalPointResponse getGeographicalPoint(UUID geographicalPointId) {
        return geographicalPointMapper.toResponse(
                geographicalPointRepository.findById(geographicalPointId).orElseThrow(GeographicalPointNotFoundException::new)
        );
    }

    @Override
    public boolean correctTaxiCallGeographicalPoints(List<GeographicalPointEntity> geographicalPoints) {
        Set<GeographicalPointEntity> geographicalPointsSet = new HashSet<>(geographicalPoints);
        return geographicalPointsSet.size() == geographicalPoints.size();
    }
}
