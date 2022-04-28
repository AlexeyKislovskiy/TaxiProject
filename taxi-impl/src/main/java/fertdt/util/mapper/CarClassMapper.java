package fertdt.util.mapper;

import fertdt.dto.response.CarClassResponse;
import fertdt.model.CarClassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CarClassMapper {
    CarClassResponse toResponse(CarClassEntity carClassEntity);

    @Named("toCarClassEntityById")
    default CarClassEntity toCarClassEntityById(UUID id) {
        return CarClassEntity.builder().uuid(id).build();
    }
}
