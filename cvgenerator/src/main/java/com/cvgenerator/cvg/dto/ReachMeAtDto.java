package com.cvgenerator.cvg.dto;

import com.cvgenerator.cvg.enums.ContactType;
import com.cvgenerator.cvg.service.ReachMeAtService;
import com.cvgenerator.cvg.service.impl.ReachMeAtServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReachMeAtDto
 * Created On : 5/13/2024 7:35 AM
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReachMeAtDto {
    private Integer reachMeAtId;

    private ContactType contactType;

    private String details;

    private Integer basicInformationId;
}
