package com.cvgenerator.cvg.enums;

import java.util.ArrayList;
import java.util.List;

public enum Gender {
    MALE, FEMALE, OTHERS;

    public static List<String> getGenderList() {
        Gender[] genders = Gender.values();
        List<String> stringList = new ArrayList<>();
        for (Gender g : genders) {
            stringList.add(g.name());
        }
        return stringList;
    }
}
