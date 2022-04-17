package fertdt.dto.response;

import fertdt.validation.annotation.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseResponse {
    private String surname;

    private String name;

    private String patronymic;

    private String dateOfBirth;

    private String placeOfBirth;

    private String dateOfIssue;

    private String expirationDate;

    private String departmentName;

    private String idNumber;

    private String placeOfResidence;

    private Set<String> vehicleCategories;

    private String driver;
}
