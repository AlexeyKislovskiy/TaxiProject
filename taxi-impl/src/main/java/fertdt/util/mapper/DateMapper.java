package fertdt.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface DateMapper {
    @Named("toDateByString")
    default LocalDate toDateByString(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.uuuu"));
    }
}
