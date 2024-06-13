package com.cvgenerator.cvg.service;

import com.cvgenerator.cvg.dto.SkillDto;

import java.util.List;

public interface SkillService extends GenericService<SkillDto, Integer> {
    List<SkillDto> findByBasicInformationId(Integer basicInformationId);
}
