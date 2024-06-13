package com.cvgenerator.cvg.dto.error;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationInformationErrorDto {
    private boolean containsError;
    private String instituteAddress;
    private String instituteName;
    private String levelDetail;
    private String divisionOrGrade;
    private String fromYearDate;
    private String toDate;
    private String isRunning;
}
