package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.EducationInformationConverter;
import com.cvgenerator.cvg.dto.EducationInformationDto;
import com.cvgenerator.cvg.entity.EducationInformation;
import com.cvgenerator.cvg.repo.EducationInformationRepo;
import com.cvgenerator.cvg.service.EducationInformationService;
import com.cvgenerator.cvg.validation.EducationInformationValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class EducationInformationServiceImpl implements EducationInformationService {
    private final EducationInformationRepo educationInformationRepo;
    private final EducationInformationConverter educationInformationConverter;

    public EducationInformationServiceImpl(EducationInformationRepo educationInformationRepo, EducationInformationConverter educationInformationConverter) {
        this.educationInformationRepo = educationInformationRepo;
        this.educationInformationConverter = educationInformationConverter;
    }

    @Override
    public EducationInformationDto save(EducationInformationDto educationInformationDto) {
//        Map<String, String> validationError = EducationInformationValidation.validate(educationInformationDto);
//        if (validationError.isEmpty()) {
            EducationInformation entity = educationInformationConverter.toEntity(educationInformationDto);
            entity = educationInformationRepo.save(entity);
            log.info("Education Information saved with id: {}", entity.getId());
            return educationInformationConverter.toDto(entity);
//        } else {
//            log.info("Invalid Education Information DTO: {}", educationInformationDto);
//            throw new RuntimeException("validation failed!!!");
//        }
    }

    @Override
    public EducationInformationDto findById(Integer id) {
        Optional<EducationInformation> optionalEducationInformation = educationInformationRepo.findById(id);
        if (optionalEducationInformation.isPresent()) {
            EducationInformation educationInformation = optionalEducationInformation.get();
            return educationInformationConverter.toDto(educationInformation);
        } else {
            log.info("Invalid Id: {}", id);
            return null;
        }
    }

    @Override
    public List<EducationInformationDto> findAll() {
        List<EducationInformation> educationInformationList = educationInformationRepo.findAll();
        List<EducationInformationDto> educationInformationDtoList = new ArrayList<>();
        for (EducationInformation educationInformation : educationInformationList) {
            educationInformationDtoList.add(educationInformationConverter.toDto(educationInformation));
        }
        return educationInformationDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<EducationInformation> optionalEducationInformation = educationInformationRepo.findById(id);
        if (optionalEducationInformation.isPresent()) {
            educationInformationRepo.deleteById(id);
            log.info("Education information deleted with id: {}", id);
        } else {
            log.info("Invalid Id: {}", id);
        }
    }

    @Override
    public List<EducationInformationDto> findByBasicInformationId(Integer basicInformationId) {
        List<EducationInformation> educationInformationList = educationInformationRepo.findByBasicInformationId(basicInformationId);
        List<EducationInformationDto> educationInformationDtoList = new ArrayList<>();
        for (EducationInformation educationInformation : educationInformationList) {
            educationInformationDtoList.add(educationInformationConverter.toDto(educationInformation));
        }
        return educationInformationDtoList;
    }
}
