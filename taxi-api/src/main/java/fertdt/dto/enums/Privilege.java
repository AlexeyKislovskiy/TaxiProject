package fertdt.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Privilege {

    READ("Read"),
    WRITE("Write"),
    CREATE("Create"),
    DELETE("Delete"),
    ALL("All");

    private final String description;
}
