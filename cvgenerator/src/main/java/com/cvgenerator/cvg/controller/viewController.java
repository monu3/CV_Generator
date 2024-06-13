package com.cvgenerator.cvg.controller;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.pdf.ViewPagePdfConverter;
import com.cvgenerator.cvg.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("view-all")
public class viewController {

    private final BasicInformationService basicInformationService;
    private final EducationInformationService educationInformationService;
    private final ReachMeAtService reachMeAtService;
    private final ExperienceService experienceService;
    private final SkillService skillService;
    private final ViewPagePdfConverter cvHtmlToPdf;
    private final ProjectsService projectsService;

    public viewController(BasicInformationService basicInformationService, EducationInformationService educationInformationService, ReachMeAtService reachMeAtService, ExperienceService experienceService, SkillService skillService, ViewPagePdfConverter cvHtmlToPdf, ProjectsService projectsService) {
        this.basicInformationService = basicInformationService;
        this.educationInformationService = educationInformationService;
        this.reachMeAtService = reachMeAtService;
        this.experienceService = experienceService;
        this.skillService = skillService;
        this.cvHtmlToPdf = cvHtmlToPdf;
        this.projectsService = projectsService;
    }

    @GetMapping("/{basicInformationId}")
    public String openViewAlPage(@PathVariable("basicInformationId") Integer basicInformationId,
                                 Model model) throws IOException {
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        model.addAttribute("basicInformationDto", basicInformationDto);
        model.addAttribute("educationInformationDto", educationInformationService.findByBasicInformationId(basicInformationId));
        model.addAttribute("reachMeAtDto", reachMeAtService.findByBasicInformationId(basicInformationId));
//        model.addAttribute("experienceDto", experienceService.findByBasicInformationId(basicInformationId));
        model.addAttribute("skillDto", skillService.findByBasicInformationId(basicInformationId));

        List<ExperienceDto> experienceDto = experienceService.findByBasicInformationId(basicInformationId);
        Map<Integer, List<ProjectsDto>> experienceProjectsMap = new HashMap<>();
        for( ExperienceDto experience :experienceDto){
            Integer experienceId= experience.getExperienceId();
            List<ProjectsDto> projectDtoList = projectsService.findByExperienceId(experienceId);
            experienceProjectsMap.put(experienceId,projectDtoList);
        }
        model.addAttribute("experienceDto",experienceDto);
        model.addAttribute("experienceProjectsMap", experienceProjectsMap);
        return "viewAll/view_All";
    }
    @GetMapping("/skill-view/{basicInfoId}")
    public String openSkillViewPage(@PathVariable("basicInfoId") Integer basicInfoId,Model model){
        model.addAttribute("skillDto",skillService.findByBasicInformationId(basicInfoId));
        return "skill/skill_view";
    }

    @GetMapping("/downloadCvInPdf/{basicInformationId}")
    public String downloadCvInPdf(@PathVariable("basicInformationId") Integer basicInformationId, Model model) {
        try {
            String message = "PDF generated successfully.";

            // Adjust this according to your template name
            String templateName = "viewAll/download_view_page";
            //calling function from  ViewPagePdfConverter class
            cvHtmlToPdf.generatePdf(basicInformationId, templateName);
            model.addAttribute("basicInformationDtoList", basicInformationService.findAll());
            model.addAttribute("message", message);
        } catch (IOException e) {
            model.addAttribute("message", "Failed to generate PDF.");
        }
        return "redirect:/basic-info";
    }
}
