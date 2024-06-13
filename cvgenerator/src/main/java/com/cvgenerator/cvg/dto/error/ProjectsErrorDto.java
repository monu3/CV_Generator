package com.cvgenerator.cvg.dto.error;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsErrorDto {

    private boolean containsError;
    private String projectNameError;
    private String roleInProjectError;
    private String descriptionError;
    private String liveUrlPathError;
    private String techStackUsedError;

}
