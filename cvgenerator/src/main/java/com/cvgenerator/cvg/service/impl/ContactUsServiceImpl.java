package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.ContactUsConverter;
import com.cvgenerator.cvg.dto.ContactUsDto;
import com.cvgenerator.cvg.entity.ContactUs;
import com.cvgenerator.cvg.repo.ContactUsRepo;
import com.cvgenerator.cvg.service.ContactUsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ContactUsServiceImpl
 * Created On : 5/28/2024 6:29 PM
 **/
@Slf4j
@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ContactUsRepo contactUsRepo;
    private final ContactUsConverter contactUsConverter;

    public ContactUsServiceImpl(ContactUsRepo contactUsRepo, ContactUsConverter contactUsConverter) {
        this.contactUsRepo = contactUsRepo;
        this.contactUsConverter = contactUsConverter;
    }

    @Override
    public ContactUsDto save(ContactUsDto contactUsDto) {
        ContactUs entity = contactUsConverter.toEntity(contactUsDto);
        entity = contactUsRepo.save(entity);
        return contactUsDto;
    }

    @Override
    public ContactUsDto findById(Integer id) throws IOException {
        Optional<ContactUs> entity = contactUsRepo.findById(id);
        if (entity.isPresent()) {
            ContactUs contactUs = entity.get();
            return contactUsConverter.toDto(contactUs);
        } else {
            log.error("Contact Us Not Found");
            return null;
        }
    }

    @Override
    public List<ContactUsDto> findAll() throws IOException {
        List<ContactUs> contactUsList = contactUsRepo.findAll();
        List<ContactUsDto> contactUsDtoList = new ArrayList<>();
        for (ContactUs contactUs : contactUsList) {
            contactUsDtoList.add(contactUsConverter.toDto(contactUs));
        }
        return contactUsDtoList;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
