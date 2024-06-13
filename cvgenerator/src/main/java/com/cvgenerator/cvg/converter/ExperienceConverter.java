package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.entity.Experience;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Builder
@Component
@Slf4j
public class ExperienceConverter extends AbstractConverter<ExperienceDto, Experience> {

    //injecting dependency to use suitable
    private final LocalDateUtils localDateUtils;

    private final BasicInformationRepo basicInformationRepo;

    public ExperienceConverter(LocalDateUtils localDateUtils, BasicInformationRepo basicInformationRepo) {
        this.localDateUtils = localDateUtils;
        this.basicInformationRepo = basicInformationRepo;
    }


    //converting entity to the Dto
    @Override
    public ExperienceDto toDto(Experience experience) {

        // use builder method if there is no logic used in the code for setter and getter
        return ExperienceDto
                .builder()
                .experienceId(experience.getExperienceId())
                .companyName(experience.getCompanyName())
                .companyWebsite(experience.getCompanyWebsite())
                .address(experience.getAddress())
                .contact(experience.getContact())
                .startDate(String.valueOf(experience.getStartDate()))
                .endDate(String.valueOf(experience.getEndDate()))
                .position(experience.getPosition())
                .jobRole(experience.getJobRole())
                .isCurrent(experience.getIsCurrent())
                .basicInformationId(experience.getBasicInformation().getId())
                .experienceId(experience.getExperienceId())
                .build();
    }

    //converting Dto into Entity
    @Override
    public Experience toEntity(ExperienceDto experienceDto) {

        if(experienceDto==null){
            return null;
        }
        Experience entity = new Experience();
        entity.setExperienceId(experienceDto.getExperienceId());
        entity.setAddress(experienceDto.getAddress());
        entity.setContact(experienceDto.getContact());
        entity.setPosition(experienceDto.getPosition());
        entity.setCompanyName(experienceDto.getCompanyName());
        entity.setCompanyWebsite(experienceDto.getCompanyWebsite());
        entity.setJobRole(experienceDto.getJobRole());
        entity.setIsCurrent(experienceDto.getIsCurrent());

        entity.setStartDate(localDateUtils.convertStringToDate(experienceDto.getStartDate()));
            entity.setEndDate(localDateUtils.convertStringToDate(experienceDto.getEndDate()));
        //using optional to Rid from null Exception
        Optional<BasicInformation> optionalBasicInformation = basicInformationRepo.findById(experienceDto.getBasicInformationId());
        if (optionalBasicInformation.isPresent()) {
            BasicInformation basicInformation = optionalBasicInformation.get();
            entity.setBasicInformation(basicInformation);
        } else {
            log.error("BasicInformation not found");
            throw new RuntimeException("Basic information not found !!");
        }
        return entity;
    }
}
