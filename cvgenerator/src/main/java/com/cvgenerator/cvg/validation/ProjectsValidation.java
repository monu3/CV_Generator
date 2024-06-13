package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.dto.error.ProjectsErrorDto;
import com.cvgenerator.cvg.entity.Projects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ProjectsValidation {

    public ProjectsErrorDto isProjectsValid(ProjectsDto projectsDto) {

        ProjectsErrorDto projectsErrorDto = new ProjectsErrorDto();

        String projectNameMessage = validateProjectName(projectsDto.getProjectName());
        if (projectNameMessage != null) {
            projectsErrorDto.setContainsError(true);
            projectsErrorDto.setProjectNameError(projectNameMessage);
        }

        String roleInProjectMessage = validateRoleInProject(projectsDto.getRoleInProject());
        if (roleInProjectMessage != null) {
            projectsErrorDto.setContainsError(true);
            projectsErrorDto.setRoleInProjectError(roleInProjectMessage);
        }

        String descriptionMessage = validateDescription(projectsDto.getDescription());
        if (descriptionMessage != null) {
            projectsErrorDto.setContainsError(true);
            projectsErrorDto.setDescriptionError(descriptionMessage);
        }

        String liveUrlPathMessage = validateLiveUrlPath(projectsDto.getLiveUrlPath());
        if (liveUrlPathMessage != null) {
            projectsErrorDto.setContainsError(true);
            projectsErrorDto.setLiveUrlPathError(liveUrlPathMessage);
        }

        String techStackUsedMessage = validateTechStackUsed(projectsDto.getTechStackUsed());
        if (techStackUsedMessage != null) {
            projectsErrorDto.setContainsError(true);
            projectsErrorDto.setTechStackUsedError(techStackUsedMessage);
        }
        return projectsErrorDto;
    }


    private String validateTechStackUsed(String techStackUsed) {
        if (techStackUsed == null) {
            return "techStackUsed is null";
        } else if (techStackUsed.length() > 50) {
            return "techStackUsed is exceeds 50 character !!";
        }
        return null;
    }

    private String validateLiveUrlPath(String liveUrlPath) {
        if (liveUrlPath == null || liveUrlPath.isEmpty()) {
            return "liveUrlPath is null or empty";
        } else if (liveUrlPath.length() > 100) {
            return "liveUrlPath is exceeds 100 !!";
        }
        return null;
    }

    private String validateDescription(String description) {
        if (description == null) {
            return "Description is empty";
        } else if (description.length() > 300) {
            return "Description is too long !!";
        }
        return null;

    }

    private String validateRoleInProject(String roleInProject) {
        if (roleInProject == null) {
            return "ROLE IN PROJECT IS NULL";
        } else if (roleInProject.length() > 50) {
            return "ROLE IN PROJECT MUST BE less than 50 character !!";
        }
        return null;
    }

    private String validateProjectName(String projectName) {
        if (projectName == null || projectName.isEmpty()) {
            return "Project name cannot be empty";
        } else if (projectName.length() > 100) {
            return "Project name cannot exceed 100 characters !!";
        }
        return null;
    }

}
