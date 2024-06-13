package com.cvgenerator.cvg.controller;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.EducationInformationDto;
import com.cvgenerator.cvg.dto.error.EducationInformationErrorDto;
import com.cvgenerator.cvg.enums.EducationType;
import com.cvgenerator.cvg.enums.Level;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.service.EducationInformationService;
import com.cvgenerator.cvg.validation.EducationInformationValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("education-info")

public class EducationInformationController {
    private final EducationInformationService educationInformationService;
    private final BasicInformationService basicInformationService;
    private final EducationInformationValidation educationInformationValidation;

    public EducationInformationController(EducationInformationService educationInformationService,
                                          BasicInformationService basicInformationService,
                                          EducationInformationValidation educationInformationValidation) {
        this.educationInformationService = educationInformationService;
        this.basicInformationService = basicInformationService;
        this.educationInformationValidation = educationInformationValidation;
    }

    @GetMapping("/add-education-info/{basicInformationId}")
    public String openEducationInformationListPage(@PathVariable("basicInformationId") Integer basicInformationId,
                                                   RedirectAttributes redirectAttributes) {

        EducationInformationDto educationInformationDto = new EducationInformationDto();
        educationInformationDto.setBasicInformationId(basicInformationId);

        redirectAttributes.addFlashAttribute("educationInformationDto", educationInformationDto);
        redirectAttributes.addAttribute("basicInformationId", basicInformationId);
        return "redirect:/education-info/open-form";
    }

    @GetMapping("/open-form")
    public String openEducationInformationFormPage(Integer basicInformationId,
                                                   Model model) {

        if (!model.containsAttribute("educationInformationDto")) {
            model.addAttribute("educationInformationDto", new EducationInformationDto());
        }

        if (!model.containsAttribute("errorObject")) {
            model.addAttribute("errorObject", new EducationInformationErrorDto());
        }

        model.addAttribute("levelList", Level.getLevelList());
        model.addAttribute("educationTypeList", EducationType.getEducationType());
        model.addAttribute("educationInformationId", basicInformationId);

        return "educationInformation/form_page";
    }

    @PostMapping("/save")
    public String saveEducationInformation(@ModelAttribute EducationInformationDto educationInformationDto,
                                           RedirectAttributes redirectAttributes) {

        EducationInformationErrorDto educationInformationErrorDto = educationInformationValidation.isInformationErrorDtoValid(educationInformationDto);
        if (!educationInformationErrorDto.isContainsError()) {
            educationInformationDto = educationInformationService.save(educationInformationDto);
            redirectAttributes.addAttribute("basicInformationId", educationInformationDto.getBasicInformationId());
            return "redirect:/education-info/id/{basicInformationId}";
        } else {
            redirectAttributes.addFlashAttribute("errorObject", educationInformationErrorDto);
            redirectAttributes.addAttribute("basicInformationId", educationInformationDto.getBasicInformationId());
            redirectAttributes.addFlashAttribute("educationInformationDto", educationInformationDto);
            return "redirect:/education-info/open-form";
        }
    }

    @GetMapping("/id/{basicInformationId}")
    public String findEducationInformationById(@PathVariable("basicInformationId") Integer basicInformationId,
                                               Model model) throws IOException {
        List<EducationInformationDto> educationInformationDtoList = educationInformationService.findByBasicInformationId(basicInformationId);
        model.addAttribute("educationInformationDtoList", educationInformationDtoList);
        StringBuilder fullName = new StringBuilder("Education Information of ");
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        fullName.append(basicInformationDto.getFirstName());
        if (basicInformationDto.getMiddleName() != null) {
            fullName.append(" ").append(basicInformationDto.getMiddleName());
        }
        fullName.append(" ").append(basicInformationDto.getLastName());
        model.addAttribute("fullName", fullName);

        model.addAttribute("educationInformationDtoList", educationInformationDtoList);
        return "educationInformation/list_page";
    }

    @GetMapping("/edit/{educationInfoId}")
    public String editEducationInfoById(@PathVariable("educationInfoId") Integer educationInfoId,
                                        RedirectAttributes redirectAttributes) throws IOException {
        EducationInformationDto educationInformationDto = educationInformationService.findById(educationInfoId);
        redirectAttributes.addFlashAttribute("educationInformationDto", educationInformationDto);
        redirectAttributes.addAttribute("basicInformationId", educationInformationDto.getBasicInformationId());
        return "redirect:/education-info/open-form";
    }

    @GetMapping("/delete/{educationInformationId}")
    public String deleteEducationInformationById(@PathVariable("educationInformationId") Integer educationInformationId,
                                                 RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addAttribute("basicInformationID",
                educationInformationService.findById(educationInformationId).getBasicInformationId());
        educationInformationService.deleteById(educationInformationId);
        return "redirect:/education-info/id/{basicInformationID}";
    }
}
