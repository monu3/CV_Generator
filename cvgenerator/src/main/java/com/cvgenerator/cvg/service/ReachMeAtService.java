package com.cvgenerator.cvg.service;

import com.cvgenerator.cvg.dto.ReachMeAtDto;

import java.util.List;

public interface ReachMeAtService extends GenericService<ReachMeAtDto, Integer> {
    List<ReachMeAtDto> findByBasicInformationId(Integer basicInformationId);
}
