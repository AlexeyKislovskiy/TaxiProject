package fertdt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver_license")
public class DriverLicenseEntity extends AbstractEntity {
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @Column(name = "date_of_issue", nullable = false)
    private LocalDate dateOfIssue;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "id_number", nullable = false, unique = true)
    private String idNumber;

    @Column(name = "place_of_residence", nullable = false)
    private String placeOfResidence;

    @ManyToMany
    @JoinTable(
            name = "driver_license_vehicle_category",
            joinColumns = @JoinColumn(name = "driver_license_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_category_id", referencedColumnName = "uuid"))
    private Set<VehicleCategoryEntity> vehicleCategories;

    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "uuid")
    private DriverEntity driver;
}