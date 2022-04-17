package fertdt.dto.request;

import fertdt.validation.annotation.Date;
import fertdt.validation.annotation.ValueFromArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportRequest {
    @NotBlank(message = "Series can not be blank")
    @Pattern(regexp = "[0-9]{4}", message = "Series should consist of 4 digits")
    private String series;

    @NotBlank(message = "Number can not be blank")
    @Pattern(regexp = "[0-9]{6}", message = "Number should consist of 6 digits")
    private String number;

    @NotBlank(message = "Date of issue can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2022",
            message = "Date of issue should be date in {pattern} format between {min} and {max}")
    private String dateOfIssue;

    @NotBlank(message = "Department name can not be blank")
    private String departmentName;

    @NotBlank(message = "Department code can not be blank")
    private String departmentCode;

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

    @NotBlank(message = "Sex can not be blank")
    @ValueFromArray(possibleValues = {"MALE", "FEMALE"}, message = "Sex should be from {possibleValues}")
    private String sex;

    @NotNull(message = "Driver id can not be null")
    private UUID driverId;
}
