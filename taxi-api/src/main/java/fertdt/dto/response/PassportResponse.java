package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Паспорт")
public class PassportResponse {
    @ApiModelProperty(value = "Серия паспорта", example = "1234")
    private String series;

    @ApiModelProperty(value = "Номер паспорта", example = "123456")
    private String number;

    @ApiModelProperty(value = "Дата выдачи", example = "01.01.2014")
    private String dateOfIssue;

    @ApiModelProperty(value = "Наименование подразделения, выдавшего паспорт", example = "Some Kazan UFMS")
    private String departmentName;

    @ApiModelProperty(value = "Код подразделения, выдавшего паспорт", example = "292-000")
    private String departmentCode;

    @ApiModelProperty(value = "Фамилия", example = "Kislovskiy")
    private String surname;

    @ApiModelProperty(value = "Имя", example = "Alexey")
    private String name;

    @ApiModelProperty(value = "Отчество", example = "Dmitrievich")
    private String patronymic;

    @ApiModelProperty(value = "Дата рождения", example = "01.01.2000")
    private String dateOfBirth;

    @ApiModelProperty(value = "Место рождения", example = "Kazan")
    private String placeOfBirth;

    @ApiModelProperty(value = "Пол", example = "MALE")
    private String sex;

    @ApiModelProperty(value = "Юзернейм водителя", example = "fertdt")
    private String driver;
}
