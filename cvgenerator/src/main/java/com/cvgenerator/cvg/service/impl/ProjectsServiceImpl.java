package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.ProjectsConverter;
import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.entity.Projects;
import com.cvgenerator.cvg.repo.ExperienceRepo;
import com.cvgenerator.cvg.repo.ProjectsRepo;
import com.cvgenerator.cvg.service.ProjectsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectsServiceImpl implements ProjectsService {


    private final ProjectsRepo projectsRepo;
    private final ExperienceRepo experienceRepo;

    private final ProjectsConverter projectsConverter;


    public ProjectsServiceImpl(ProjectsRepo projectsRepo, ExperienceRepo experienceRepo, ProjectsConverter projectsConverter) {
        this.projectsRepo = projectsRepo;
        this.experienceRepo = experienceRepo;
        this.projectsConverter = projectsConverter;
    }

    @Override
    public ProjectsDto save(ProjectsDto projectsDto) {

        Projects entity = projectsConverter.toEntity(projectsDto);
        entity = projectsRepo.save(entity);

        log.info("Projects saved with id : {}", entity.getProjectId());

        return projectsConverter.toDto(entity);

    }

    @Override
    public ProjectsDto findById(Integer id) {

        Optional<Projects> projectsOptional = projectsRepo.findById(id);
        if (projectsOptional.isPresent()) {
            Projects projects = projectsOptional.get();
            return projectsConverter.toDto(projects);

        } else {
            log.error("Invalid Id: {}", id);
            return null;
        }
    }

    @Override
    public List<ProjectsDto> findAll() {
        List<Projects> projectsList = this.projectsRepo.findAll();
        List<ProjectsDto> projectsDtoList = new ArrayList<>();

        for (Projects projects : projectsList) {
            projectsDtoList.add(projectsConverter.toDto(projects));
        }
        return projectsDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Projects> optionalProjects = projectsRepo.findById(id);
        if (optionalProjects.isPresent()) {
            projectsRepo.deleteById(id);
            log.info("Successfully deleted with id :{}", id);
        } else {
            log.error("Invalid id: {}", id);
        }
    }

    @Override
    public List<ProjectsDto> findByExperienceId(Integer experienceId) {
        List<Projects> projectsList = projectsRepo.findByExperienceId(experienceId);
        List<ProjectsDto> projectsDtoList = new ArrayList<>();
        for (Projects projects : projectsList) {
            projectsDtoList.add(projectsConverter.toDto(projects));
        }

        return projectsDtoList;
    }
}
