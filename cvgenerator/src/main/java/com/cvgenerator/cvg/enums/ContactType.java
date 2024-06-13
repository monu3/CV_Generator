package com.cvgenerator.cvg.enums;

import java.util.ArrayList;
import java.util.List;

public enum ContactType {
    MOBILE_NUMBER, LINKED_IN_URL, VIBER_NUMBER, WHATS_APP_NUMBER, EMAIL;

    public static List<String> getContactTypes() {
        ContactType[] contactTypes = ContactType.values();
        List<String> contactTypesList = new ArrayList<>();
        for (ContactType c : contactTypes) {
            contactTypesList.add(c.toString());
        }
        return contactTypesList;
    }
}
