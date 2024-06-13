package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.EducationInformationDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.entity.EducationInformation;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class EducationInformationConverter extends AbstractConverter<EducationInformationDto, EducationInformation> {


    private final BasicInformationRepo basicInformationRepo;
    private final LocalDateUtils localDateUtils;

    public EducationInformationConverter(BasicInformationRepo basicInformationRepo, LocalDateUtils localDateUtils) {
        this.basicInformationRepo = basicInformationRepo;
        this.localDateUtils = localDateUtils;
    }

    @Override
    public EducationInformationDto toDto(EducationInformation educationInformation) {
        if (educationInformation == null) {
            return null;
        }
        return EducationInformationDto
                .builder()
                .id(educationInformation.getId())
                .basicInformationId(educationInformation.getBasicInformation().getId())
                .instituteName(educationInformation.getInstituteName())
                .instituteAddress(educationInformation.getInstituteAddress())
                .level(educationInformation.getLevel())
                .levelDetail(educationInformation.getLevelDetail())
                .divisionOrGrade(educationInformation.getDivisionOrGrade())
                .fromYearDate(String.valueOf(educationInformation.getFromYearDate()))
                .toYearDate(String.valueOf(educationInformation.getToYearDate()))
                .isRunning(educationInformation.getIsRunning())
                .educationType(educationInformation.getEducationType())
                .build();
    }

    @Override
    public EducationInformation toEntity(EducationInformationDto educationInformationDto) {

        if (educationInformationDto == null) {
            return null;
        }
        EducationInformation entity = new EducationInformation();

        entity.setId(educationInformationDto.getId());
        entity.setInstituteName(educationInformationDto.getInstituteName());
        entity.setInstituteAddress(educationInformationDto.getInstituteAddress());
        entity.setLevel(educationInformationDto.getLevel());
        entity.setLevelDetail(educationInformationDto.getLevelDetail());
        entity.setDivisionOrGrade(educationInformationDto.getDivisionOrGrade());
        entity.setFromYearDate(localDateUtils.convertStringToDate(educationInformationDto.getFromYearDate()));
        entity.setToYearDate(localDateUtils.convertStringToDate(educationInformationDto.getToYearDate()));
        entity.setIsRunning(educationInformationDto.getIsRunning());
        entity.setEducationType(educationInformationDto.getEducationType());

        Optional<BasicInformation> optionalBasicInformation = basicInformationRepo.findById(educationInformationDto.getBasicInformationId());
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

