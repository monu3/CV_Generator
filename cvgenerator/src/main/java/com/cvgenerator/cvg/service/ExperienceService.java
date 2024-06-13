package com.cvgenerator.cvg.service;

import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.dto.ReachMeAtDto;

import java.util.List;

public interface ExperienceService extends GenericService<ExperienceDto,Integer>{
    List<ExperienceDto> findByBasicInformationId(Integer basicInformationId);
}
