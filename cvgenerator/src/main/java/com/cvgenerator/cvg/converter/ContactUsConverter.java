package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.ContactUsDto;
import com.cvgenerator.cvg.entity.ContactUs;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ContactUsConverter
 * Created On : 6/5/2024 12:24 PM
 **/
@Component
public class ContactUsConverter extends AbstractConverter<ContactUsDto, ContactUs> {

    @Override
    public ContactUsDto toDto(ContactUs contactUs) throws IOException {
        return ContactUsDto.builder()
                .id(contactUs.getId())
                .firstName(contactUs.getFirstName())
                .middleName(contactUs.getMiddleName())
                .lastName(contactUs.getLastName())
                .address(contactUs.getAddress())
                .phoneNumber(contactUs.getPhoneNumber())
                .email(contactUs.getEmail())
                .isActive(contactUs.getIsActive())
                .build();
    }

    @Override
    public ContactUs toEntity(ContactUsDto contactUsDto) {
       return ContactUs.builder()
               .id(contactUsDto.getId())
               .firstName(contactUsDto.getFirstName())
               .middleName(contactUsDto.getMiddleName())
               .lastName(contactUsDto.getLastName())
               .address(contactUsDto.getAddress())
               .phoneNumber(contactUsDto.getPhoneNumber())
               .email(contactUsDto.getEmail())
               .isActive(contactUsDto.getIsActive())
               .build();
    }
}

