package com.cvgenerator.cvg.dto;


import com.cvgenerator.cvg.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicInformationDto {
    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String dateOfBirth;

    private Gender gender;

    private String religion;

    private String nationality;

    private String currentAddress;

    private String background;

    private MultipartFile photoFile;
    private String photoPath;

    public BasicInformationDto(Integer id) {
        this.id = id;
    }
}
