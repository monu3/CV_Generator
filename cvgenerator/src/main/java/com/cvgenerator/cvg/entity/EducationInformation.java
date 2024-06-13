package com.cvgenerator.cvg.entity;

import com.cvgenerator.cvg.enums.EducationType;
import com.cvgenerator.cvg.enums.Level;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@Table(name = "education_information")
@NoArgsConstructor
@AllArgsConstructor
public class EducationInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "institute_Name", nullable = false, length = 50)
    private String instituteName;
    @Column(name = "institute_address", nullable = false, length = 150)
    private String instituteAddress;
    @Column(length = 9)
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(name = "level_detail", nullable = false, length = 100)
    private String levelDetail;
    @Column(name = "division_or_grade", length = 12)
    private String divisionOrGrade;
    @Column(name = "from_year_date", nullable = false)
    private LocalDate fromYearDate;
    @Column(name = "to_year_date")
    private LocalDate toYearDate;
    @Column(name = "is_running")
    private Boolean isRunning;
    @Column(length = 9)
    @Enumerated(EnumType.STRING)
    private EducationType educationType;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = BasicInformation.class
    )
    @JoinColumn(
            name = "basic_info_id",
            foreignKey = @ForeignKey(
                    name = "fk_educationinformation_id"
            )
    )
    private BasicInformation basicInformation;
}
