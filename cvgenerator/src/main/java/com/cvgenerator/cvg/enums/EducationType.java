package com.cvgenerator.cvg.enums;

import java.util.ArrayList;
import java.util.List;

public enum EducationType {
    REGULAR, TRAINING;

    public static List<String> getEducationType() {
        EducationType[] educationTypes = EducationType.values();
        List<String> stringList = new ArrayList<>();
        for (EducationType educationType : educationTypes) {
            stringList.add(educationType.name());
        }
        return stringList;
    }
}
