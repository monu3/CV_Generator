package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.SkillConverter;
import com.cvgenerator.cvg.dto.SkillDto;
import com.cvgenerator.cvg.entity.Skill;
import com.cvgenerator.cvg.repo.SkillRepo;
import com.cvgenerator.cvg.service.SkillService;
import com.cvgenerator.cvg.validation.SkillValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepo skillRepo;
    private final SkillConverter skillConverter;


    public SkillServiceImpl(SkillRepo skillRepo, SkillConverter skillConverter) {
        this.skillRepo = skillRepo;
        this.skillConverter = skillConverter;
    }

    @Override
    public SkillDto save(SkillDto skillDto) {
        Skill entity = skillConverter.toEntity(skillDto);
        entity = skillRepo.save(entity);
        log.info("Skill saved with id: {}", entity.getSkillId());
        return skillConverter.toDto(entity);
    }

    @Override
    public SkillDto findById(Integer id) {
        Optional<Skill> optionalSkill = skillRepo.findById(id);
        if (optionalSkill.isPresent()) {
            Skill skill = optionalSkill.get();
            return skillConverter.toDto(skill);
        } else {
            log.error("Invalid id: {}", id);
            return null;
        }
    }

    @Override
    public List<SkillDto> findAll() {
        List<Skill> skillList = skillRepo.findAll();
        List<SkillDto> skillDtoList = new ArrayList<>();
        for (Skill skill : skillList) {
            skillDtoList.add(skillConverter.toDto(skill));
        }
        return skillDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Skill> optionalSkill = skillRepo.findById(id);
        if (optionalSkill.isPresent()) {
            skillRepo.deleteById(id);
            log.info("Skill: {}", id);
        } else {
            log.error("Invalid ID: {}", id);
        }
    }

    @Override
    public List<SkillDto> findByBasicInformationId(Integer basicInformationId) {
        List<Skill> skillList = skillRepo.findByBasicInformationId(basicInformationId);
        List<SkillDto> skillDtoList = new ArrayList<>();
        for (Skill skill : skillList) {
            skillDtoList.add(skillConverter.toDto(skill));
        }
        return skillDtoList;
    }
}
