package com.cvgenerator.cvg.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExperienceErrorDto {

    private boolean containsError;
    private String companyNameError;
    private String companyWebsiteError;
    private String companyAddressError;
    private String companyContactError;
    private String positionError;
    private String jobRoleError;
}
