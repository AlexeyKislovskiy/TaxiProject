package fertdt.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring")
public interface DateMapper {
    @Named("toDateByString")
    default LocalDate toDateByString(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.uuuu"));
    }

    @Named("plusDaysToNow")
    default Instant plusDaysToNow(Integer days) {
        return Instant.now().plus(days, ChronoUnit.DAYS);
    }
}
