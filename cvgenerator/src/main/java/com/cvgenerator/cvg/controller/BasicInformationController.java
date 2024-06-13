package com.cvgenerator.cvg.controller;


import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.error.BasicInformationErrorDto;
import com.cvgenerator.cvg.enums.Gender;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.validation.BasicInformationValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("basic-info")
public class BasicInformationController {
    private final BasicInformationService basicInformationService;
    private final BasicInformationValidation basicInformationValidation;

    public BasicInformationController(BasicInformationService basicInformationService,
                                      BasicInformationValidation basicInformationValidation) {
        this.basicInformationService = basicInformationService;
        this.basicInformationValidation = basicInformationValidation;
    }

    @GetMapping()
    public String openBasicInformationListPage(Model model) throws IOException {
        model.addAttribute("basicInformationDtoList", basicInformationService.findAll());
        return "basicinformation/list_page";
    }

    @GetMapping("/open-form")
    public String openBasicInformationFormPage(Model model) {
        if (!model.containsAttribute("basicInformationDto")) {
            model.addAttribute("basicInformationDto", new BasicInformationDto());
        }
        if (!model.containsAttribute("errorObject")) {
            model.addAttribute("errorObject", new BasicInformationErrorDto());
        }
        model.addAttribute("genderList", Gender.getGenderList());
        return "basicinformation/form_page";
    }

    @PostMapping("/save")
    public String saveBasicInfo(@ModelAttribute BasicInformationDto basicInformationDto,
                                RedirectAttributes redirectAttributes) {

        // lets go for validation
        BasicInformationErrorDto basicInformationErrorDto = basicInformationValidation.isBasicInformationValid(basicInformationDto);
        if (!basicInformationErrorDto.isContainsError()) {
            basicInformationDto = basicInformationService.save(basicInformationDto);
            String message;
            if (basicInformationDto != null) {
                message = basicInformationDto.getId() == null ? "Saved successfully" : " Updated successfully";
                redirectAttributes.addFlashAttribute("message", message);
                return "redirect:/basic-info";
            } else {
                message = basicInformationDto.getId() == null ? "Save failed" : " Update failed";
                redirectAttributes.addFlashAttribute("message", message);
                return "redirect:/basic-info/open-form";
            }


        } else {
            redirectAttributes.addFlashAttribute("errorObject", basicInformationErrorDto);
        }
        redirectAttributes.addFlashAttribute("basicInformationDto", basicInformationDto);
        return "redirect:/basic-info/open-form";
    }

    @GetMapping("/edit/{basicInformationId}")
    public String editBasicInformation(@PathVariable("basicInformationId") Integer basicInformationId,
                                       RedirectAttributes redirectAttributes) throws IOException {
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        redirectAttributes.addFlashAttribute("basicInformationDto", basicInformationDto);
        return "redirect:/basic-info/open-form";
    }

    @GetMapping("/delete/{basicInformationId}")
    public String deleteBasicInformationById(@PathVariable("basicInformationId") Integer basicInformationId) {
        basicInformationService.deleteById(basicInformationId);
        return "redirect:/basic-info";
    }


}
