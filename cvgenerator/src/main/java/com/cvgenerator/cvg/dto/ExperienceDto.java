package com.cvgenerator.cvg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDto {


    private Integer experienceId;

    private String companyName;

    private String companyWebsite;

    private String address;

    private String contact;

    private String startDate;

    private String endDate;

    private String position;

    private String jobRole;

    private Boolean isCurrent;

    private Integer basicInformationId;


    public ExperienceDto(Integer experienceId) {
        this.experienceId = experienceId;
    }
}
