package com.cvgenerator.cvg.controller;


import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.dto.error.ExperienceErrorDto;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.service.ExperienceService;
import com.cvgenerator.cvg.validation.ExperienceValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("experience-info")
public class ExperienceController {


    private final ExperienceService experienceService;
    private final ExperienceValidation experienceValidation;
    private final BasicInformationService basicInformationService;

    public ExperienceController(ExperienceService experienceService, ExperienceValidation experienceValidation, BasicInformationService basicInformationService) {
        this.experienceService = experienceService;
        this.experienceValidation = experienceValidation;
        this.basicInformationService = basicInformationService;
    }

    @GetMapping("/add-experience-info/{basicInformationId}")
    public String openExperienceListPage(@PathVariable("basicInformationId") Integer basicInformationId, RedirectAttributes redirectAttributes) {

        ExperienceDto experienceDto = new ExperienceDto();
        experienceDto.setBasicInformationId(basicInformationId);

        redirectAttributes.addFlashAttribute("experienceDto", experienceDto);
        redirectAttributes.addAttribute("basicInformationId", basicInformationId);
        return "redirect:/experience-info/open-form";
    }

    @GetMapping("/open-form")
    public String openExperienceFormPage(Integer basicInformationId, Model model) {
        if (!model.containsAttribute("experienceDto")) {
            model.addAttribute("experienceDto", new ExperienceDto());
        }
        if (!model.containsAttribute("errorObject")) {
            model.addAttribute("errorObject", new ExperienceErrorDto());
        }
        model.addAttribute("basicInformationId", basicInformationId);
        return "experience/form_page";
    }


    @PostMapping("/save")
    public String saveExperience(@ModelAttribute ExperienceDto experienceDto, RedirectAttributes redirectAttributes) {

        ExperienceErrorDto experienceErrorDto = experienceValidation.isExperienceValid(experienceDto);
        if (!experienceErrorDto.isContainsError()) {
            experienceDto = experienceService.save(experienceDto);
            redirectAttributes.addAttribute("basicInformationId", experienceDto.getBasicInformationId());
            return "redirect:/experience-info/id/{basicInformationId}";
        } else {
            redirectAttributes.addFlashAttribute("errorObject", experienceErrorDto);
            redirectAttributes.addAttribute("basicInformationId", experienceDto.getBasicInformationId());
        }
        redirectAttributes.addFlashAttribute("experienceDto", experienceDto);
        return "redirect:/experience-info/open-form";
    }


    @GetMapping("/id/{basicInformationId}")
    public String findExperienceById(@PathVariable("basicInformationId") Integer basicInformationId, Model model) throws IOException {
        List<ExperienceDto> experienceDtoList = experienceService.findByBasicInformationId(basicInformationId);
        model.addAttribute("experienceDtoList", experienceDtoList);

        StringBuilder fullName = new StringBuilder("Experience information of  ");
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        fullName.append(basicInformationDto.getFirstName());
        if (basicInformationDto.getMiddleName() != null) {
            fullName.append(" ").append(basicInformationDto.getMiddleName());
        }
        fullName.append(" ").append(basicInformationDto.getLastName());
        model.addAttribute("fullName", fullName);

        return "experience/list_page";
    }

    @GetMapping("edit/{experienceId}")
    public String editExperienceInformationById(@PathVariable("experienceId") Integer experienceId, RedirectAttributes redirectAttributes) throws IOException {
        ExperienceDto experienceDto = experienceService.findById(experienceId);
        redirectAttributes.addFlashAttribute("experienceDto", experienceDto);
        redirectAttributes.addAttribute("basicInformationId", experienceDto.getBasicInformationId());
        return "redirect:/experience-info/open-form";
    }

    @GetMapping("delete/{experienceId}")
    public String deleteExperienceByExperienceId(@PathVariable("experienceId") Integer experienceId, RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addAttribute("basicInformationID", experienceService.findById(experienceId).getBasicInformationId());
        experienceService.deleteById(experienceId);
        return "redirect:/experience-info/id/{basicInformationID}";
    }

}
