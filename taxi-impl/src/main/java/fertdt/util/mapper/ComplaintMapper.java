package fertdt.util.mapper;

import fertdt.dto.response.ComplaintResponse;
import fertdt.model.ComplaintEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {
    ComplaintResponse toResponse(ComplaintEntity complaintEntity);
}
