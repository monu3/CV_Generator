package com.cvgenerator.cvg.pdf;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.service.*;
import com.cvgenerator.cvg.utils.PdfGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ViewPagePdfConverter {
    private final BasicInformationService basicInformationService;
    private final EducationInformationService educationInformationService;
    private final ExperienceService experienceService;
    private final SkillService skillService;
    private final ReachMeAtService reachMeAtService;
    private final ProjectsService projectsService;

    public ViewPagePdfConverter(BasicInformationService basicInformationService, EducationInformationService educationInformationService, ExperienceService experienceService, SkillService skillService, ReachMeAtService reachMeAtService, ProjectsService projectsService) {
        this.basicInformationService = basicInformationService;
        this.educationInformationService = educationInformationService;
        this.experienceService = experienceService;
        this.skillService = skillService;
        this.reachMeAtService = reachMeAtService;
        this.projectsService = projectsService;
    }

    public void generatePdf(Integer basicInformationId, String templateName) throws IOException {
        StringBuilder message = new StringBuilder("CV_");
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        if (basicInformationDto == null) {
            log.error("BasicInformationDto is null for id: {}", basicInformationId);
            throw new RuntimeException("Basic information not found for id: " + basicInformationId);
        }
        message.append(basicInformationDto.getFirstName());
        if (basicInformationDto.getMiddleName() != null) {
            message.append("_").append(basicInformationDto.getMiddleName());
        }
        message.append("_").append(basicInformationDto.getLastName());

        String outputFileName = System.getProperty("user.home") + File.separator + message + ".pdf";
        File pdfFile = new File(outputFileName);
        // Check if the file already exists and delete it
        if (pdfFile.exists()) {
            pdfFile.delete();
        }
        Context context = new Context();
        context.setVariable("basicInformationId", basicInformationId);
        context.setVariable("basicInformationDto", basicInformationDto);
        context.setVariable("educationInformationDto", educationInformationService.findByBasicInformationId(basicInformationId));
        context.setVariable("experienceDto", experienceService.findByBasicInformationId(basicInformationId));

        List<ExperienceDto> experienceDto = experienceService.findByBasicInformationId(basicInformationId);
        Map<Integer, List<ProjectsDto>> experienceProjectsMap = new HashMap<>();

        for( ExperienceDto experience :experienceDto){
            Integer experienceId= experience.getExperienceId();
            List<ProjectsDto> projectsDtoList = projectsService.findByExperienceId(experienceId);
            experienceProjectsMap.put(experienceId,projectsDtoList);
        }
        context.setVariable("experienceDto",experienceDto);
        context.setVariable("experienceProjectsMap", experienceProjectsMap);
        context.setVariable("skillDto", skillService.findByBasicInformationId(basicInformationId));
        context.setVariable("reachMeAtDto", reachMeAtService.findByBasicInformationId(basicInformationId));
        context.setVariable("message", message.toString());
        String htmlContent = PdfGeneratorUtils.parseThymeleafTemplate(templateName, context);
        PdfGeneratorUtils.generatePdfFromHtml(htmlContent, outputFileName);
    }
}