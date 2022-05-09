package fertdt.util.mapper;

import fertdt.dto.response.GeographicalPointResponse;

import java.util.List;

public interface GeocodingMapper {
    List<GeographicalPointResponse> toResponse(String json);
}
