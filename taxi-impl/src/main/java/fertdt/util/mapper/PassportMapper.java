package fertdt.util.mapper;

import fertdt.dto.request.PassportRequest;
import fertdt.dto.request.TaxiParkRequest;
import fertdt.dto.response.PassportResponse;
import fertdt.model.DriverEntity;
import fertdt.model.PassportEntity;
import fertdt.model.TaxiParkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "dateOfIssue", qualifiedByName = "toDateByString")
    @Mapping(target = "dateOfBirth", qualifiedByName = "toDateByString")
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "toDriverEntityById")
    PassportEntity toEntity(PassportRequest passportRequest);

    @Mapping(target = "driver", source = "passportEntity.driver.username")
    PassportResponse toResponse(PassportEntity passportEntity);

    @Named("toDriverEntityById")
    default DriverEntity toDriverEntityById(UUID id) {
        return DriverEntity.builder().uuid(id).build();
    }

    @Named("toDateByString")
    default LocalDate toDateByString(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.uuuu"));
    }
}
