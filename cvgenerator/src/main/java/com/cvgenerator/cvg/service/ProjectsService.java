package com.cvgenerator.cvg.service;

import com.cvgenerator.cvg.dto.ProjectsDto;

import java.util.List;


public interface ProjectsService extends GenericService<ProjectsDto,Integer>{

    List<ProjectsDto> findByExperienceId(Integer experienceId);
}
