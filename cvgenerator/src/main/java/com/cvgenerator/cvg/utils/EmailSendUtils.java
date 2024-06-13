package com.cvgenerator.cvg.utils;

import com.cvgenerator.cvg.dto.EmailDto;
import org.springframework.stereotype.Component;

@Component
public class EmailSendUtils {
    public String sendPdfToEmail(EmailDto emailDto){
        try{
            return  "Pdf send successfully!";
        }catch(Exception ex){
           return "Failed to send email";
        }
    }
}
