package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequest {
    @NotNull(message = "Taxi ride id can not be null")
    private UUID taxiRideId;

    @NotNull(message = "User id can not be null")
    private UUID userId;

    @NotNull(message = "Complaint text can not be null")
    @NotBlank(message = "Complaint text can not be blank")
    private String text;
}
