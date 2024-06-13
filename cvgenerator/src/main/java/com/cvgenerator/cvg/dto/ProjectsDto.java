package com.cvgenerator.cvg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectsDto {


    private Integer projectId;

    private String projectName;

    private String roleInProject;

    private String description;

    private Boolean isRunning;

    private String liveUrlPath;

    private String techStackUsed;

    private Integer experienceId;

    public ProjectsDto(Integer projectId) {
        this.projectId= projectId;
    }
}
