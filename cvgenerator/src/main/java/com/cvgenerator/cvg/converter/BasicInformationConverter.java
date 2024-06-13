package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.utils.FileStoreUtils;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class BasicInformationConverter extends AbstractConverter<BasicInformationDto, BasicInformation> {
    private final LocalDateUtils localDateUtils;
    private final FileStoreUtils fileStoreUtils;


    public BasicInformationConverter(LocalDateUtils localDateUtils, FileStoreUtils fileStoreUtils) {
        this.localDateUtils = localDateUtils;
        this.fileStoreUtils = fileStoreUtils;
    }

    @Override
    public BasicInformationDto toDto(BasicInformation basicInformation) throws IOException {
        return BasicInformationDto.builder()
                .id(basicInformation.getId())
                .firstName(basicInformation.getFirstName())
                .middleName(basicInformation.getMiddleName())
                .lastName(basicInformation.getLastName())
                .dateOfBirth(String.valueOf(basicInformation.getDateOfBirth()))
                .gender(basicInformation.getGender())
                .religion(basicInformation.getReligion())
                .nationality(basicInformation.getNationality())
                .currentAddress(basicInformation.getCurrentAddress())
                .background(basicInformation.getBackground())
                .photoPath(fileStoreUtils.getBase64FileFromPhotoPath(basicInformation.getPhotoPath()))
                .build();
    }

    @Override
    public BasicInformation toEntity(BasicInformationDto basicInformationDto) {

        BasicInformation entity = new BasicInformation();

        entity.setId(basicInformationDto.getId());
        entity.setFirstName(basicInformationDto.getFirstName());
        entity.setLastName(basicInformationDto.getLastName());
        entity.setMiddleName(basicInformationDto.getMiddleName());
        entity.setDateOfBirth(localDateUtils.convertStringToDate(basicInformationDto.getDateOfBirth()));
        entity.setGender(basicInformationDto.getGender());
        entity.setReligion(basicInformationDto.getReligion());
        entity.setNationality(basicInformationDto.getNationality());
        entity.setCurrentAddress(basicInformationDto.getCurrentAddress());
        entity.setBackground(basicInformationDto.getBackground());
        entity.setGender(basicInformationDto.getGender());
       // entity.setPhotoPath(basicInformationDto.getPhotoPath());
        return entity;
    }
}
