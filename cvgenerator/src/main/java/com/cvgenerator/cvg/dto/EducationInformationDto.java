package com.cvgenerator.cvg.dto;

import com.cvgenerator.cvg.enums.EducationType;
import com.cvgenerator.cvg.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInformationDto {
    private Integer id;

    private String instituteName;

    private String instituteAddress;

    private Level level;

    private String levelDetail;

    private String divisionOrGrade;

    private String fromYearDate;

    private String toYearDate;

    private Boolean isRunning;

    private EducationType educationType;

    public EducationInformationDto(Integer id) {
        this.id = id;
    }

    private Integer basicInformationId;
    ;

}
