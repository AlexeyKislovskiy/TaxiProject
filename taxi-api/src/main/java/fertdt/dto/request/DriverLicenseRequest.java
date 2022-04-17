package fertdt.dto.request;

import fertdt.validation.annotation.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseRequest {
    @NotBlank(message = "Surname can not be blank")
    private String surname;

    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotBlank(message = "Patronymic can not be blank")
    private String patronymic;

    @NotBlank(message = "Date of birth can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2008",
            message = "Date of birth should be date in {pattern} format between {min} and {max}")
    private String dateOfBirth;

    @NotBlank(message = "Place of birth can not be blank")
    private String placeOfBirth;

    @NotBlank(message = "Date of issue can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2022",
            message = "Date of issue should be date in {pattern} format between {min} and {max}")
    private String dateOfIssue;

    @NotBlank(message = "Expiration date can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2032",
            message = "Expiration date should be date in {pattern} format between {min} and {max}")
    private String expirationDate;

    @NotBlank(message = "Department name can not be blank")
    private String departmentName;

    @NotBlank(message = "Id number can not be blank")
    @Pattern(regexp = "[0-9]{10}", message = "Id number should consist of 10 digits")
    private String idNumber;

    @NotBlank(message = "Place of residence can not be blank")
    private String placeOfResidence;

    @NotNull(message = "Vehicle categories can not be null")
    @Size(min = 1, message = "Driver license should contain at least one vehicle category")
    private Set<UUID> vehicleCategoryIds;

    @NotNull(message = "Driver id can not be null")
    private UUID driverId;
}
