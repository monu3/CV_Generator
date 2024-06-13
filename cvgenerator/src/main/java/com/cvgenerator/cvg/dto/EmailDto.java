package com.cvgenerator.cvg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private String subject;
    private String receiver;
    private String message;
    private MultipartFile cvPdf;
}
