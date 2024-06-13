package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.error.BasicInformationErrorDto;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class BasicInformationValidation {
    private final LocalDateUtils localDateUtils;

    public BasicInformationValidation(LocalDateUtils localDateUtils) {
        this.localDateUtils = localDateUtils;
    }


    public BasicInformationErrorDto isBasicInformationValid(BasicInformationDto basicInformationDto) {
        BasicInformationErrorDto basicInformationErrorDto = new BasicInformationErrorDto();

        String firstNameMessage = validateFirstName(basicInformationDto.getFirstName());
        String middleNameMessage = validateMiddleName(basicInformationDto.getMiddleName());
        String lastNameMessage = validateLastName(basicInformationDto.getLastName());
        String religionMessage = validateReligionError(basicInformationDto.getReligion());
        String nationalityMessage = validateNationalityError(basicInformationDto.getNationality());
        String currentAddressMessage = validateCurrentAddressError(basicInformationDto.getCurrentAddress());
        String backgroundMessage = validateBackgroundError(basicInformationDto.getBackground());
        String dateMessage = validateDateError(basicInformationDto.getDateOfBirth());

        method(basicInformationErrorDto, firstNameMessage, middleNameMessage, lastNameMessage, religionMessage, nationalityMessage, currentAddressMessage, backgroundMessage, dateMessage);
        return basicInformationErrorDto;
    }

    public void method(BasicInformationErrorDto basicInformationErrorDto,
                       String firstNameMessage,
                       String middleNameMessage,
                       String lastNameMessage,
                       String religionMessage,
                       String nationalityMessage,
                       String currentAddressMessage,
                       String backgroundMessage,
                       String dateMessage) {
        if (firstNameMessage != null) {
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setFirstNameError(firstNameMessage);
        }
        if (middleNameMessage != null) {
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setMiddleNameError(middleNameMessage);
        }
        if (lastNameMessage != null) {
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setLastNameError(lastNameMessage);
        }
        if (religionMessage != null) {
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setReligionError(religionMessage);
        }
        if (nationalityMessage != null) {
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setNationalityError(nationalityMessage);
        }
        if (currentAddressMessage != null) {
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setCurrentAddressError(currentAddressMessage);
        }
        if (backgroundMessage != null) {
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setBackgroundError(backgroundMessage);
        }
        if(dateMessage != null){
            basicInformationErrorDto.setContainsError(true);
            basicInformationErrorDto.setDateError(dateMessage);
        }
    }

    private String validateBackgroundError(String background) {
        if (background == null) {
            return "Background cannot be null";
        } else if (background.length() > 1000) {
            return ("Background length cannot exceed more than 1000");
        }
        return null;
    }

    private String validateCurrentAddressError(String currentAddress) {
        if (currentAddress == null) {
            return "Current Address cannot be null";
        } else if (currentAddress.length() > 150) {
            return ("Current Address length cannot exceed more than 1000");
        }
        return null;
    }

    private String validateNationalityError(String nationality) {
        if (nationality == null) {
            return "Nationality cannot be null";
        } else if (nationality.length() > 30) {
            return ("Nationality length cannot exceed more than 150");
        }
        return null;
    }

    private String validateReligionError(String religion) {
        if (religion == null) {
            return "Religion cannot be null";
        } else if (religion.length() > 20) {
            return ("Religion length cannot exceed more than 1000");
        }
        return null;
    }

    private String validateLastName(String lastName) {
        if (lastName == null) {
            return "Last Name cannot be null";
        } else if (lastName.length() > 30) {
            return "Last Name length cannot exceed more than 30";
        }
        return null;
    }

    private String validateMiddleName(String middleName) {
        if (middleName == null) {
            return "Background cannot be null";
        } else if (middleName.length() > 1000) {
            return ("Middle Name length cannot exceed more than 1000");
        }
        return null;
    }

    private String validateFirstName(String firstName) {
        if (firstName == null) {
            return "First name cannot be null";
        } else if (firstName.length() > 30) {
            return "Last Name length cannot exceed more than 30";
        }
        return null;
    }

    private String validateDateError(String dateOfBirth) {
        if (dateOfBirth == null){
            return "Date of birth cannot be null";
        } else if (localDateUtils.convertStringToDate(dateOfBirth).isAfter(LocalDate.now())) {
            return "Date cannot be of future";
        }
        return null;
    }
}
