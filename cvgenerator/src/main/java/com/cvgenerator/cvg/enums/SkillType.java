package com.cvgenerator.cvg.enums;

import java.util.ArrayList;
import java.util.List;

public enum SkillType {
    SOFT_SKILL,
    TECHNICAL_SKILL;

    public static List<String> getSkillType() {
        SkillType[] skillTypes=SkillType.values();
        List<String> stringList=new ArrayList<>();
        for(SkillType skill: skillTypes){
            stringList.add(skill.name());
        }
        return stringList;
    }
}
