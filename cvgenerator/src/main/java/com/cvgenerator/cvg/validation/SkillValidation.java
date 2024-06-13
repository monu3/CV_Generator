package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.SkillDto;
import com.cvgenerator.cvg.dto.error.SkillErrorDto;
import com.cvgenerator.cvg.entity.Skill;
import com.cvgenerator.cvg.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Column;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SkillValidation {
    private final SkillService skillService;

    public SkillValidation(SkillService skillService) {
        this.skillService = skillService;
    }

    //    public Map<String, String> isSkillValid(SkillDto skillDto) {
//        Map<String, String> errorMap = new HashMap<>();
//        List<SkillDto> skillDtoList = skillService.findByBasicInformationId(skillDto.getBasicInfoId());
//        for (SkillDto skillDtoVal : skillDtoList) {
//            if (skillDto.getSkillName().equalsIgnoreCase(skillDtoVal.getSkillName()) ) {
//                if ( !skillDtoVal.getSkillId().equals(skillDto.getSkillId())){
//                    errorMap.put("duplicateSkillErrorMessage", "Skill already exist!!");
//                }
//            }
//        }
//        if (skillDto.getSkillName().length() > 50) {
//            errorMap.put("skillNameLengthValidationMessage", "skillName length cannot be more than 50");
//        }
//        if (skillDto.getSkillDescription().length() > 1000) {
//            errorMap.put("descriptionLengthValidationMessage", "Description must not exceed the length more than 1000!!!");
//        }
//        return errorMap;
//    }
    public SkillErrorDto isSkillValid(SkillDto skillDto) {
        SkillErrorDto skillErrorDto = new SkillErrorDto();
        List<SkillDto> skillDtoList = skillService.findByBasicInformationId(skillDto.getBasicInfoId());
        String skillNameMessage = validateSkillName(skillDto.getSkillName(), skillDtoList,skillDto.getSkillId());
        String skillDescriptionMessage = validateSkillDescription(skillDto.getSkillDescription());
        method(skillErrorDto,skillNameMessage,skillDescriptionMessage);
        return skillErrorDto;
    }

    public void method(SkillErrorDto skillErrorDto,
                       String skillNameMessage,
                       String skillDescriptionMessage) {
        if(skillNameMessage != null){
            skillErrorDto.setContainsError(true);
            skillErrorDto.setSkillNameError(skillNameMessage);
        }
        if(skillDescriptionMessage!=null){
            skillErrorDto.setContainsError(true);
            skillErrorDto.setSkillDescriptionError(skillDescriptionMessage);
        }
    }
    private String validateSkillName(String skillName,List<SkillDto> skillDtoList,Integer skillId){
        if(skillName== null){
            return "Skill name must not be null";
        }
        if(skillName.length()>50){
            return "Skill name length cannot exceed more than 50";
        }
        for (SkillDto skillDtoVal : skillDtoList) {
            if (skillName.equalsIgnoreCase(skillDtoVal.getSkillName()) ) {
                if ( !skillDtoVal.getSkillId().equals(skillId)){
                    return "Skill already exist!!";
                }
            }
        }
        return null;
    }
    private String validateSkillDescription(String skillDescription){
        if(skillDescription==null){
            return "Skill description must not be null";
        }
        if(skillDescription.length()>1000){
            return "Skill description length cannot exceed more tha 1000";
        }
        return null;
    }
}
