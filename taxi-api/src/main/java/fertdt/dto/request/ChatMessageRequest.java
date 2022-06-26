package fertdt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private String time;

    @NotNull(message = "User id can not be null")
    private String userId;

    @NotBlank(message = "Text can not be blank")
    private String text;
}
