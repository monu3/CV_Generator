package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.EducationInformationDto;
import com.cvgenerator.cvg.dto.error.EducationInformationErrorDto;
import com.cvgenerator.cvg.service.EducationInformationService;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class EducationInformationValidation {
    private final LocalDateUtils localDateUtils;
    private final EducationInformationService educationInformationService;

    public EducationInformationValidation(LocalDateUtils localDateUtils, EducationInformationService educationInformationService) {
        this.localDateUtils = localDateUtils;
        this.educationInformationService = educationInformationService;
    }

    public EducationInformationErrorDto isInformationErrorDtoValid(EducationInformationDto educationInformationDto) {
        EducationInformationErrorDto educationInformationErrorDto = new EducationInformationErrorDto();

        String instituteNameMessage = validateInstituteName(educationInformationDto.getInstituteName(), educationInformationDto);
        String instituteAddressMessage = validateInstituteAddress(educationInformationDto.getInstituteAddress());
        String levelDetailMessage = validateLevelDetail(educationInformationDto.getLevelDetail());
        String divisionOrGradeMessage = validateDivisionOrGrade(educationInformationDto.getDivisionOrGrade(), educationInformationDto.getIsRunning());
        String fromYearDateMessage = validateFromDate(educationInformationDto.getFromYearDate());
        String toYearDateMessage = validateToYearDate(educationInformationDto.getFromYearDate(), educationInformationDto.getToYearDate(), educationInformationDto.getIsRunning());

        if (fromYearDateMessage != null) {
            educationInformationErrorDto.setContainsError(true);
            educationInformationErrorDto.setFromYearDate(fromYearDateMessage);
        }
        if (toYearDateMessage != null) {
            educationInformationErrorDto.setContainsError(true);
            educationInformationErrorDto.setToDate(toYearDateMessage);
        }
        if (instituteNameMessage != null) {
            educationInformationErrorDto.setContainsError(true);
            educationInformationErrorDto.setInstituteName(instituteNameMessage);
        }
        if (instituteAddressMessage != null) {
            educationInformationErrorDto.setContainsError(true);
            educationInformationErrorDto.setInstituteAddress(instituteAddressMessage);
        }
        if (instituteAddressMessage != null) {
            educationInformationErrorDto.setContainsError(true);
            educationInformationErrorDto.setLevelDetail(levelDetailMessage);
        }
        if (instituteAddressMessage != null) {
            educationInformationErrorDto.setContainsError(true);
            educationInformationErrorDto.setDivisionOrGrade(divisionOrGradeMessage);
        }
        return educationInformationErrorDto;
    }

    private String validateToYearDate(String fromYearDate, String toYearDate, boolean isRunning) {
        if (!isRunning) {

            if (toYearDate == null) {
                return "To year date cannot be null";
            } else if (localDateUtils.convertStringToDate(toYearDate).isAfter(LocalDate.now())) {
                return "Date cannot be of future";
            } else if ((localDateUtils.convertStringToDate(toYearDate).isBefore(localDateUtils.convertStringToDate(fromYearDate)))) {
                return "To year date cannot be before from year date";
            }
        }
        return null;
    }

    private String validateFromDate(String fromYearDate) {
        if (fromYearDate == null) {
            return "From year date cannot be null";
        } else if (localDateUtils.convertStringToDate(fromYearDate).isAfter(LocalDate.now())) {
            return "Date cannot be of future";
        }
        return null;
    }

    private String validateInstituteName(String instituteName, EducationInformationDto educationInformationDto) {

        if (instituteName == null) {
            return "Institute Name cannot be null";
        } else if (instituteName.length() > 50) {
            return "The Institute Name length must be less than 50";
        }
        List<EducationInformationDto> educationInformationDtoList = educationInformationService.findByBasicInformationId(educationInformationDto.getBasicInformationId());
        for (EducationInformationDto existingDto : educationInformationDtoList) {
            if (existingDto.getInstituteName().equalsIgnoreCase(instituteName)) {
                if ( !existingDto.getId().equals(educationInformationDto.getId())){
                    return "Institute name already exist";
                }
            }
        }
        return null;
    }

    private String validateInstituteAddress(String instituteAddress) {
        if (instituteAddress == null) {
            return "Institute Address cannot be null";
        } else if (instituteAddress.length() > 150) {
            return "The Institute Address length must be less than 150";
        }
        return null;
    }

    private String validateLevelDetail(String levelDetail) {
        if (levelDetail == null) {
            return "Level Detail cannot be null";
        } else if (levelDetail.length() > 100) {
            return "The Level Detail length must be less than 100";
        }
        return null;
    }

    private String validateDivisionOrGrade(String divisionOrGrade, boolean isRunning) {

        if (!isRunning) {
            if (divisionOrGrade == null) {
                return "divisionOrGrade cannot be null";
            } else if (divisionOrGrade.length() > 12) {
                return "divisionOrGrade length must be less than 12";
            }
        }
        return null;
    }

}
