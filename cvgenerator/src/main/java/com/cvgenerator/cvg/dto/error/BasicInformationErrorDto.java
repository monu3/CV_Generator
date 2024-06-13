package com.cvgenerator.cvg.dto.error;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.sax.SAXResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInformationErrorDto {
    private boolean containsError;
    private String firstNameError;
    private String middleNameError;
    private String lastNameError;
    private String religionError;
    private String nationalityError;
    private String currentAddressError;
    private String backgroundError;
    private String dateError;
}
