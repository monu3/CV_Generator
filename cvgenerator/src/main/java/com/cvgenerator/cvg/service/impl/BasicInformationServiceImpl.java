package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.BasicInformationConverter;
import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.utils.FileStoreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BasicInformationServiceImpl implements BasicInformationService {

    private final BasicInformationRepo basicInformationRepo;
    private final FileStoreUtils fileStoreUtils;

    private final BasicInformationConverter basicInformationConverter;

    public BasicInformationServiceImpl(BasicInformationRepo basicInformationRepo, FileStoreUtils fileStoreUtils,
                                       BasicInformationConverter basicInformationConverter) {
        this.basicInformationRepo = basicInformationRepo;
        this.fileStoreUtils = fileStoreUtils;
        this.basicInformationConverter = basicInformationConverter;
    }

    @Override
    public BasicInformationDto save(BasicInformationDto basicInformationDto) {
        // data validation
        // TODO data validation
        // file validation
        String photoFilePath;
        BasicInformation entity = basicInformationConverter.toEntity(basicInformationDto);

        // TODO
        /*
            If you are here to create new
            You must have a file

            If you are here to update, File is not mandatory.
            During update you can use old file path.
         */
        // file store --> file path
        if (basicInformationDto.getId() == null || basicInformationDto.getPhotoFile() != null) {
            photoFilePath = fileStoreUtils.uploadFile(basicInformationDto.getPhotoFile());
        } else {
            Optional<BasicInformation> basicInformationDto1 = basicInformationRepo.findById(basicInformationDto.getId());
            photoFilePath = basicInformationDto1.get().getPhotoPath(); ;
        }
        entity.setPhotoPath(photoFilePath);

        entity = basicInformationRepo.save(entity);

        return new BasicInformationDto(entity.getId());
    }

    @Override
    public BasicInformationDto findById(Integer id) throws IOException {
        Optional<BasicInformation> optionalBasicInformation = basicInformationRepo.findById(id);
        if (optionalBasicInformation.isPresent()) {
            BasicInformation basicInformation = optionalBasicInformation.get();
            return basicInformationConverter.toDto(basicInformation);
        } else {
            log.info("Invalid Id: {}", id);
            return null;
        }
    }

    @Override
    public List<BasicInformationDto> findAll() throws IOException {
        List<BasicInformation> basicInformationList = basicInformationRepo.findAll();
        List<BasicInformationDto> basicInformationDtoList = new ArrayList<>();
        for (BasicInformation basicInformation : basicInformationList) {
            basicInformationDtoList.add(basicInformationConverter.toDto(basicInformation));
        }
        return basicInformationDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        basicInformationRepo.deleteById(id);
        log.info("Basic Information deleted with id: {}", id);
    }
}
