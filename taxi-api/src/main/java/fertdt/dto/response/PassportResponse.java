package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportResponse {
    private String series;

    private String number;

    private String dateOfIssue;

    private String departmentName;

    private String departmentCode;

    private String surname;

    private String name;

    private String patronymic;

    private String dateOfBirth;

    private String placeOfBirth;

    private String sex;

    private String driver;
}
