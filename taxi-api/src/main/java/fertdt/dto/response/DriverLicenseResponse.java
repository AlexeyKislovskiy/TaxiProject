package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Водительские права")
public class DriverLicenseResponse {
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

    @ApiModelProperty(value = "Дата выдачи", example = "01.01.2018")
    private String dateOfIssue;

    @ApiModelProperty(value = "Дата окончания срока действия", example = "01.01.2028")
    private String expirationDate;

    @ApiModelProperty(value = "Наименование подразделения, выдавшего удостоверение", example = "GIBDD 6701")
    private String departmentName;

    @ApiModelProperty(value = "Номер удостоверения", example = "1234567890")
    private String idNumber;

    @ApiModelProperty(value = "Место жительства", example = "Kazan")
    private String placeOfResidence;

    @ApiModelProperty(value = "Список категорий транспортных средств, на право управления которыми выдано удостоверение")
    private Set<VehicleCategoryResponse> vehicleCategories;

    @ApiModelProperty(value = "Юзернейм водителя", example = "fertdt")
    private String driver;
}
