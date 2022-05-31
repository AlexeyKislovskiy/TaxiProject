package fertdt.dto.request;

import fertdt.validation.annotation.Date;
import fertdt.validation.annotation.ValueFromArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Паспорт")
public class PassportRequest {
    @ApiModelProperty(value = "Серия паспорта", example = "1234")
    @NotBlank(message = "Series can not be blank")
    @Pattern(regexp = "[0-9]{4}", message = "Series should consist of 4 digits")
    private String series;

    @ApiModelProperty(value = "Номер паспорта", example = "123456")
    @NotBlank(message = "Number can not be blank")
    @Pattern(regexp = "[0-9]{6}", message = "Number should consist of 6 digits")
    private String number;

    @ApiModelProperty(value = "Дата выдачи", example = "01.01.2014")
    @NotBlank(message = "Date of issue can not be blank")
    @Date(pattern = "d.M.uuuu", min = "01.01.1900", max = "15.04.2022",
            message = "Date of issue should be date in {pattern} format between {min} and {max}")
    private String dateOfIssue;

    @ApiModelProperty(value = "Наименование подразделения, выдавшего паспорт", example = "Some Kazan UFMS")
    @NotBlank(message = "Department name can not be blank")
    private String departmentName;

    @ApiModelProperty(value = "Код подразделения, выдавшего паспорт", example = "292-000")
    @NotBlank(message = "Department code can not be blank")
    private String departmentCode;

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

    @ApiModelProperty(value = "Пол", example = "MALE")
    @NotBlank(message = "Sex can not be blank")
    @ValueFromArray(possibleValues = {"MALE", "FEMALE"}, message = "Sex should be from {possibleValues}")
    private String sex;

    @ApiModelProperty(value = "ID водителя")
    @NotNull(message = "Driver id can not be null")
    private UUID driverId;
}
