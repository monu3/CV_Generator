package com.cvgenerator.cvg.utils;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.service.ReachMeAtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * HtmlToPdfConvertor
 * Created On : 5/28/2024 8:42 AM
 **/
@Slf4j
@Component
public class HtmlToPdfConvertor {
    private final ReachMeAtService reachMeAtService;
    private final BasicInformationService basicInformationService;

    public HtmlToPdfConvertor(ReachMeAtService reachMeAtService, BasicInformationService basicInformationService) {
        this.reachMeAtService = reachMeAtService;
        this.basicInformationService = basicInformationService;
    }

    private String parseThymeleafTemplate(Integer basicInformationId, String message) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("basicInformationId", basicInformationId);
        context.setVariable("reachMeAtDtoList", reachMeAtService.findByBasicInformationId(basicInformationId));


        context.setVariable("message", message);

        return templateEngine.process("reachmeat/view_page", context);
    }

    public void generatePdfFromHtml(Integer basicInformationId) throws IOException {
        StringBuilder message = new StringBuilder("Contact_Details_of ");
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        message.append(basicInformationDto.getFirstName());
        if (basicInformationDto.getMiddleName() != null) {
            message.append("_").append(basicInformationDto.getMiddleName());
        }
        message.append("_").append(basicInformationDto.getLastName());

        String html = parseThymeleafTemplate(basicInformationId, message.toString());

        String outputFolder = System.getProperty("user.home") + File.separator + message + ".pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
