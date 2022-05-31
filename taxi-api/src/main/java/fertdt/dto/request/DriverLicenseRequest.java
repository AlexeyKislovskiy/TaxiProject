package fertdt.dto.request;

import fertdt.validation.annotation.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Водительские права")
public class DriverLicenseRequest {
    @ApiModelProperty(value = "Фамилия", example = "Kislovskiy")
    @NotBlank(message = "Surname can not be blank")
    private String surname;

    @ApiModelProperty(value = "Имя", example = "Alexey")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @ApiModelProperty(value = "Отчество", example = "Dmitrievich")
    @NotBlank(message = "Patronymic can not be blank")
    private String patronymic;

    @ApiModelProperty(value = "Дата рождения", example = "01.01.2000")
    @NotBlank(message = "Date of birth can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2008",
            message = "Date of birth should be date in {pattern} format between {min} and {max}")
    private String dateOfBirth;

    @ApiModelProperty(value = "Место рождения", example = "Kazan")
    @NotBlank(message = "Place of birth can not be blank")
    private String placeOfBirth;

    @ApiModelProperty(value = "Дата выдачи", example = "01.01.2018")
    @NotBlank(message = "Date of issue can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2022",
            message = "Date of issue should be date in {pattern} format between {min} and {max}")
    private String dateOfIssue;

    @ApiModelProperty(value = "Дата окончания срока действия", example = "01.01.2028")
    @NotBlank(message = "Expiration date can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2032",
            message = "Expiration date should be date in {pattern} format between {min} and {max}")
    private String expirationDate;

    @ApiModelProperty(value = "Наименование подразделения, выдавшего удостоверение", example = "GIBDD 6701")
    @NotBlank(message = "Department name can not be blank")
    private String departmentName;

    @ApiModelProperty(value = "Номер удостоверения", example = "1234567890")
    @NotBlank(message = "Id number can not be blank")
    @Pattern(regexp = "[0-9]{10}", message = "Id number should consist of 10 digits")
    private String idNumber;

    @ApiModelProperty(value = "Место жительства", example = "Kazan")
    @NotBlank(message = "Place of residence can not be blank")
    private String placeOfResidence;

    @ApiModelProperty(value = "Список ID категорий транспортных средств, на право управления которыми выдано удостоверение",
            dataType = "List", example = "[\"37c83d0a-c9dc-48d8-b080-352ebba67e21\"," +
            " \"a42ffe43-28e9-4f2e-a2da-0d38daf90b9f\", \"4901eeaa-613c-4f0f-ae72-099af7e59d82\"]")
    @NotNull(message = "Vehicle categories can not be null")
    @Size(min = 1, message = "Driver license should contain at least one vehicle category")
    private Set<UUID> vehicleCategoryIds;

    @ApiModelProperty(value = "ID водителя")
    @NotNull(message = "Driver id can not be null")
    private UUID driverId;
}
