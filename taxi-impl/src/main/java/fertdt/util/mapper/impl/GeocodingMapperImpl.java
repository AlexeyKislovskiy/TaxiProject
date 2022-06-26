package fertdt.util.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.exception.JsonException;
import fertdt.util.mapper.GeocodingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GeocodingMapperImpl implements GeocodingMapper {
    private final ObjectMapper objectMapper;
    private final String FEATURES = "features";
    private final String PLACE_NAME = "place_name";
    private final String CENTER_COORDINATES = "center";

    @Override
    public List<GeographicalPointResponse> toResponse(String json) {
        List<GeographicalPointResponse> list = new ArrayList<>();
        try {
            JsonNode arrayNode = objectMapper.readTree(json).get(FEATURES);
            for (JsonNode objectNode : arrayNode) {
                list.add(GeographicalPointResponse.builder()
                        .name(objectNode.get(PLACE_NAME).asText())
                        .longitude(objectNode.get(CENTER_COORDINATES).get(0).asDouble())
                        .latitude(objectNode.get(CENTER_COORDINATES).get(1).asDouble())
                        .build());
            }
            return list;
        } catch (JsonProcessingException e) {
            throw new JsonException("Problem with parsing json, cannot get geographical point response");
        }
    }
}
