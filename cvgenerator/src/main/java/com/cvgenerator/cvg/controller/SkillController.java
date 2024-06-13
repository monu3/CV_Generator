package com.cvgenerator.cvg.controller;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.SkillDto;
import com.cvgenerator.cvg.dto.error.SkillErrorDto;
import com.cvgenerator.cvg.enums.SkillType;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.service.SkillService;
import com.cvgenerator.cvg.validation.SkillValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("skill-info")
public class SkillController {
    private final SkillService skillService;
    private final BasicInformationService basicInformationService;
    private final SkillValidation skillValidation;

    public SkillController(SkillService skillService, BasicInformationService basicInformationService, SkillValidation skillValidation) {
        this.skillService = skillService;
        this.basicInformationService = basicInformationService;
        this.skillValidation = skillValidation;
    }

    @GetMapping("/open-form")
    public String openSkillFormPage(Integer basicInformationId, Model model) {

        if (!model.containsAttribute("errorMap")) {
            model.addAttribute("errorMap", new SkillErrorDto());
        }
        model.addAttribute("skillType", SkillType.getSkillType());
        model.addAttribute("basicInformationId", basicInformationId);
        return "skill/skill_form_page";
    }

    @PostMapping("/save")
    public String saveSkill(@ModelAttribute SkillDto skillDto,
                            RedirectAttributes redirectAttributes, Model model) {
//        Map<String, String> errorMap = skillValidation.isSkillValid(skillDto);
        SkillErrorDto skillErrorDto=skillValidation.isSkillValid(skillDto);
        if (!skillErrorDto.isContainsError()) {
            skillDto = skillService.save(skillDto);
            redirectAttributes.addFlashAttribute("skillDto", skillDto);
            redirectAttributes.addAttribute("basicInformationId", skillDto.getBasicInfoId());
            return "redirect:/skill-info/open-list/{basicInformationId}";
        } else {
            redirectAttributes.addAttribute("basicInformationId", skillDto.getBasicInfoId());
            redirectAttributes.addFlashAttribute("skillDto", skillDto);
            redirectAttributes.addFlashAttribute("errorMap", skillErrorDto);
            return "redirect:/skill-info/open-form";
        }
    }

    @GetMapping("/open-list/{basicInfoId}")
    public String findSkillById(@PathVariable("basicInfoId") Integer basicInfoId, Model model) throws IOException {
        List<SkillDto> skillDtoList = skillService.findByBasicInformationId(basicInfoId);
        model.addAttribute("skillDtoList", skillDtoList);
        StringBuilder fullName = new StringBuilder("Skill Details of ");
        BasicInformationDto basicInformationDto= basicInformationService.findById(basicInfoId);
        fullName.append(basicInformationDto.getFirstName());
        if(basicInformationDto.getMiddleName() != null){
            fullName.append(" "+basicInformationDto.getMiddleName());
        }
        fullName.append(" "+basicInformationDto.getLastName());
        model.addAttribute("fullName",fullName);
        return "skill/skill_list_page";
    }

    @GetMapping("/edit/{skillId}")
    public String editSkill(@PathVariable("skillId") Integer skillId, RedirectAttributes redirectAttributes) throws IOException {
        SkillDto skillDto = skillService.findById(skillId);
        redirectAttributes.addFlashAttribute("skillDto", skillDto);
        redirectAttributes.addAttribute("basicInformationId", skillDto.getBasicInfoId());
        return "redirect:/skill-info/open-form";
    }

    @GetMapping("/add-skill/{basicInformationId}")
    public String addSkill(@PathVariable("basicInformationId") Integer basicInformationId, RedirectAttributes redirectAttributes) {
        SkillDto skillDto = new SkillDto();
        skillDto.setBasicInfoId(basicInformationId);
        redirectAttributes.addFlashAttribute("skillDto", skillDto);
        redirectAttributes.addAttribute("basicInformationId", basicInformationId);
        return "redirect:/skill-info/open-form";
    }

    @GetMapping("/delete-skill/{skillId}")
    public String deleteSkill(@PathVariable("skillId") Integer skillId, RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addAttribute("basicInfoId", skillService.findById(skillId).getBasicInfoId());
        skillService.deleteById(skillId);
        return "redirect:/skill-info/open-list/{basicInfoId}";
    }
}
