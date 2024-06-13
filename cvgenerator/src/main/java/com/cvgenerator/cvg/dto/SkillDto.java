package com.cvgenerator.cvg.dto;

import com.cvgenerator.cvg.enums.SkillType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {

    private Integer skillId;

    private SkillType skillType;

    private String skillName;

    private String skillDescription;

    private Integer basicInfoId;

}
