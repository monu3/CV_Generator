package com.cvgenerator.cvg.enums;

import java.util.ArrayList;
import java.util.List;

public enum Level {
    SLC_SEE, PLUS_TWO, DIPLOMA, BACHELOR, MASTER, PHD;

    public static List<String> getLevelList() {
        Level[] levels = Level.values();
        List<String> stringList = new ArrayList<>();
        for (Level level : levels) {
            stringList.add(level.name());
        }
        return stringList;
    }

}
