package com.cvgenerator.cvg.validation;


import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.dto.error.BasicInformationErrorDto;
import com.cvgenerator.cvg.dto.error.ExperienceErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExperienceValidation {

    public ExperienceErrorDto isExperienceValid(ExperienceDto experienceDto) {

        ExperienceErrorDto experienceErrorDto = new ExperienceErrorDto();

        String companyNameMessage = validateCompanyName(experienceDto.getCompanyName());
        if (companyNameMessage != null) {
            experienceErrorDto.setContainsError(true);
            experienceErrorDto.setCompanyNameError(companyNameMessage);
        }

        String companyWebsiteMessage = validateCompanyWebsite(experienceDto.getCompanyWebsite());
        if (companyWebsiteMessage != null) {
            experienceErrorDto.setContainsError(true);
            experienceErrorDto.setCompanyWebsiteError(companyWebsiteMessage);
        }

        String companyAddressError = validateCompanyAddress(experienceDto.getAddress());
        if (companyAddressError != null) {
            experienceErrorDto.setContainsError(true);
            experienceErrorDto.setCompanyAddressError(companyAddressError);
        }

        String companyContactError = validateCompanyContact(experienceDto.getContact());
        if (companyContactError != null) {
            experienceErrorDto.setContainsError(true);
            experienceErrorDto.setCompanyContactError(companyContactError);
        }

        String positionError = validatePosition(experienceDto.getPosition());
        if (positionError != null) {
            experienceErrorDto.setContainsError(true);
            experienceErrorDto.setPositionError(positionError);
        }

        String jobRoleError = validateJobRole(experienceDto.getJobRole());
        if (jobRoleError != null) {
            experienceErrorDto.setContainsError(true);
            experienceErrorDto.setJobRoleError(jobRoleError);
        }

        return experienceErrorDto;
    }

    private String validateJobRole(String jobRole) {
        if (jobRole == null) {
            return "jobRole is null";
        } else if (jobRole.length() > 100) {
            return "jobRole is exceeds 100 characters !!";
        }
        return null;
    }

    private String validatePosition(String position) {
        if (position == null) {
            return "Position is null";
        } else if (position.length() > 100) {
            return "Position is exceeds 100 characters !!";
        }
        return null;
    }

    private String validateCompanyContact(String contact) {
        if (contact == null || contact.isEmpty()) {
            return "Contact cannot be empty";
        } else if (contact.length() > 10 || contact.length() < 9) {
            return "Contact should be 10 characters !!";
        }
        return null;
    }

    private String validateCompanyAddress(String address) {
        if (address == null || address.isEmpty()) {
            return "Company address cannot be empty";
        } else if (address.length() > 100) {
            return "Company address is exceeds 100 characters !!";
        }
        return null;
    }

    private String validateCompanyWebsite(String companyWebsite) {
        if (companyWebsite == null || companyWebsite.isEmpty()) {
            return "Company website is null or empty";
        } else if (companyWebsite.length() > 100) {
            return "Company website is exceeds 100 characters !!";
        }
        return null;
    }

    private String validateCompanyName(String companyName) {
        if (companyName == null || companyName.isEmpty()) {
            return "Company name cannot be empty or null";
        } else if (companyName.length() > 100) {
            return "Company name cannot exceed 100 characters !!";
        }
        return null;
    }


}
