package com.cvgenerator.cvg.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillErrorDto {
    private  boolean containsError;
    private  String skillNameError;
    private  String skillDescriptionError;
}
