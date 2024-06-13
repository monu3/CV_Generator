package com.cvgenerator.cvg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ContactUsDto
 * Created On : 5/28/2024 6:23 PM
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactUsDto {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private Boolean isActive;

}
