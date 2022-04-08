package fertdt.util.mapper;

import fertdt.dto.response.CarClassResponse;
import fertdt.model.CarClassEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarClassMapper {
    CarClassResponse toResponse(CarClassEntity carClassEntity);
}
