package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.ReachMeAtDto;
import com.cvgenerator.cvg.enums.ContactType;
import com.cvgenerator.cvg.service.ReachMeAtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReachMeAtValidation
 * Created On : 5/13/2024 7:58 AM
 **/
@Slf4j
@Component
public class ReachMeAtValidation {
    String detailsError = "detailsErrorMessage";

    private final ReachMeAtService reachMeAtService;

    public ReachMeAtValidation(ReachMeAtService reachMeAtService) {
        this.reachMeAtService = reachMeAtService;
    }

    public Map<String, String> validate(ReachMeAtDto reachMeAtDto) {
        Map<String, String> map = new HashMap<>();
        if ((reachMeAtDto.getContactType().equals(ContactType.VIBER_NUMBER) ||
                reachMeAtDto.getContactType().equals(ContactType.MOBILE_NUMBER) ||
                reachMeAtDto.getContactType().equals(ContactType.WHATS_APP_NUMBER)) &&
                reachMeAtDto.getDetails().length() != 10) {
            map.put(detailsError, "The Number should be of length 10!!!");
        } else if ((reachMeAtDto.getContactType().equals(ContactType.LINKED_IN_URL) ||
                reachMeAtDto.getContactType().equals(ContactType.EMAIL)) &&
                (reachMeAtDto.getDetails().length() > 200)) {
            map.put(detailsError, "The Length should not exceed 200!!!");
        }

        List<ReachMeAtDto> reachMeAtDtoList =
                reachMeAtService.findByBasicInformationId(reachMeAtDto.getBasicInformationId());
        for (ReachMeAtDto reachMeAtDtoVal : reachMeAtDtoList) {
            if (reachMeAtDto.getReachMeAtId() != null &&
                    reachMeAtDto.getReachMeAtId().equals(reachMeAtDtoVal.getReachMeAtId())) {
                if (reachMeAtDto.getDetails().equals(reachMeAtDtoVal.getDetails())) {
                    map.put(detailsError, "The new details should not be same as the previous details!");
                }
            } else if (reachMeAtDto.getContactType().equals(reachMeAtDtoVal.getContactType())) {
                map.put("contactTypeErrorMessage", "This Contact Type has already been added!!!");
            }
        }
        return map;
    }
}
