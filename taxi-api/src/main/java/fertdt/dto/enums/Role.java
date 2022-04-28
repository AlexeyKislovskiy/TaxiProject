package fertdt.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("User"),
    ADMIN("Administrator");

    private final String description;

}