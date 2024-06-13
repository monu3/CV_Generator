package com.cvgenerator.cvg.service;

import com.cvgenerator.cvg.dto.EducationInformationDto;

import java.util.List;

public interface EducationInformationService extends GenericService<EducationInformationDto, Integer> {
    List<EducationInformationDto> findByBasicInformationId(Integer basicInformationId);
}
