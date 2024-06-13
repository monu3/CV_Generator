package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.entity.Experience;
import com.cvgenerator.cvg.entity.Projects;
import com.cvgenerator.cvg.repo.ExperienceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ProjectsConverter extends AbstractConverter<ProjectsDto,Projects>{

    private final ExperienceRepo experienceRepo ;

    public ProjectsConverter(ExperienceRepo experienceRepo) {
        this.experienceRepo = experienceRepo;
    }

    @Override
    public ProjectsDto toDto(Projects projects) {

        return ProjectsDto
                .builder()
                .projectId(projects.getProjectId())
                .projectName(projects.getProjectName())
                .roleInProject(projects.getRoleInProject())
                .description(projects.getDescription())
                .isRunning(projects.getIsRunning())
                .liveUrlPath(projects.getLiveUrlPath())
                .techStackUsed(projects.getTechStackUsed())
                .experienceId(projects.getExperience().getExperienceId())
                .build();
    }

    @Override
    public Projects toEntity(ProjectsDto projectsDto) {

        Projects entity = new Projects();
        entity.setProjectId(projectsDto.getProjectId());
        entity.setProjectName(projectsDto.getProjectName());
        entity.setDescription(projectsDto.getDescription());
        entity.setRoleInProject(projectsDto.getRoleInProject());
        entity.setLiveUrlPath(projectsDto.getLiveUrlPath());
        entity.setTechStackUsed(projectsDto.getTechStackUsed());
        entity.setIsRunning(projectsDto.getIsRunning());

        //it is not possible to use Experience object ...using id we extract experience object
        Optional<Experience> experienceOptional = experienceRepo.findById(projectsDto.getExperienceId());
        if(experienceOptional.isPresent()){
            Experience experience = experienceOptional.get();
            entity.setExperience(experience);
        }else {
            log.error("Experience information is not found");
            throw new RuntimeException("Experience information not found !!");
        }
        return entity;
    }
}
