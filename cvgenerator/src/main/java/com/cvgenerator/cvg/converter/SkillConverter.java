package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.SkillDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.entity.Skill;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class SkillConverter extends AbstractConverter<SkillDto, Skill> {
    private final BasicInformationRepo basicInformationRepo;

    public SkillConverter(BasicInformationRepo basicInformationRepo) {
        this.basicInformationRepo = basicInformationRepo;
    }

    @Override
    public SkillDto toDto(Skill skill) {

        return SkillDto
                .builder()
                .skillId(skill.getSkillId())
                .skillName(skill.getSkillName())
                .skillType(skill.getSkillType())
                .skillDescription(skill.getSkillDescription())
                .basicInfoId(skill.getBasicInformation().getId())
                .build();
    }

    @Override
    public Skill toEntity(SkillDto skillDto) {
        Skill entity = new Skill();
        entity.setSkillId(skillDto.getSkillId());
        entity.setSkillName(skillDto.getSkillName());
        entity.setSkillType(skillDto.getSkillType());
        entity.setSkillDescription(skillDto.getSkillDescription());
        Optional<BasicInformation> optionalBasicInformation = basicInformationRepo.findById(skillDto.getBasicInfoId());
        if (optionalBasicInformation.isPresent()) {
            BasicInformation basicInformation = optionalBasicInformation.get();
            entity.setBasicInformation(basicInformation);
        } else {
            log.error("BasicInformation not found");
            throw new RuntimeException("Basic information not found!!");
        }
        return entity;
    }
}
