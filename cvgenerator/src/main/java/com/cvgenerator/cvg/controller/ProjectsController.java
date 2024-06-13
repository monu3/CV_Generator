package com.cvgenerator.cvg.controller;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.dto.error.ProjectsErrorDto;
import com.cvgenerator.cvg.service.ExperienceService;
import com.cvgenerator.cvg.service.ProjectsService;
import com.cvgenerator.cvg.validation.ProjectsValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("projects-info")
public class ProjectsController {

    private final ProjectsService projectsService;
    private final ExperienceService experienceService;
    private final ProjectsValidation projectsValidation;


    public ProjectsController(ProjectsService projectsService, ExperienceService experienceService, ProjectsValidation projectsValidation) {
        this.projectsService = projectsService;
        this.experienceService = experienceService;
        this.projectsValidation = projectsValidation;
    }


    @GetMapping("/open-form")
    public String openProjectsInformationFormPage(Integer experienceId, Model model) {
        if (!model.containsAttribute("projectsDto")) {
            model.addAttribute("projectsDto", new ProjectsDto());
        }

        if (!model.containsAttribute("errorObject")) {
            model.addAttribute("errorObject", new ProjectsErrorDto());
        }

        model.addAttribute("experienceId", experienceId);
        return "projects/form_page";
    }


    @GetMapping("/add-projects-info/{experienceId}")
    public String openProjectInformationListPage(@PathVariable("experienceId") Integer experienceId, RedirectAttributes redirectAttributes) {
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setExperienceId(experienceId);
        redirectAttributes.addFlashAttribute("projectsDto", projectsDto);
        redirectAttributes.addAttribute("experienceId", experienceId);

        return "redirect:/projects-info/open-form";
    }

    @PostMapping("/save")
    public String saveProjectsInfo(@ModelAttribute ProjectsDto projectsDto, RedirectAttributes redirectAttributes) {

        ProjectsErrorDto projectsErrorDto = projectsValidation.isProjectsValid(projectsDto);
        if (!projectsErrorDto.isContainsError()) {
            projectsDto = projectsService.save(projectsDto);
            redirectAttributes.addAttribute("experienceId", projectsDto.getExperienceId());
            return "redirect:/projects-info/id/{experienceId}";
        } else {
            redirectAttributes.addFlashAttribute("errorObject", projectsErrorDto);
            redirectAttributes.addAttribute("experienceId", projectsDto.getExperienceId());
            //return "redirect:/projects-info/open-form";
            redirectAttributes.addFlashAttribute("projectsDto", projectsDto);
            return "redirect:/projects-info/open-form";
        }

    }

    @GetMapping("/id/{experienceId}")
    public String findProjectsById(@PathVariable("experienceId") Integer experienceId, Model model) throws IOException {
        List<ProjectsDto> projectsDtoList = projectsService.findByExperienceId(experienceId);
        model.addAttribute("projectsDtoList", projectsDtoList);
        model.addAttribute("basicInformationId", experienceService.findById(experienceId).getBasicInformationId());
        StringBuilder companyName = new StringBuilder("Projects information of  ");
        ExperienceDto experienceDto = experienceService.findById(experienceId);
        companyName.append(experienceDto.getCompanyName());
        model.addAttribute("companyName", companyName);
        return "projects/list_page";
    }

    @GetMapping("/edit/{projectId}")
    public String editProjectsInformationById(@PathVariable("projectId") Integer projectId, RedirectAttributes redirectAttributes) throws IOException {
        ProjectsDto projectsDto = projectsService.findById(projectId);
        redirectAttributes.addFlashAttribute("projectsDto", projectsDto);
        redirectAttributes.addAttribute("experienceId", projectsDto.getExperienceId());
        return "redirect:/projects-info/open-form";
    }

    @GetMapping("/delete/{projectId}")
    public String deleteProjectByProjectId(@PathVariable("projectId") Integer projectId, RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addAttribute("experienceId", projectsService.findById(projectId).getExperienceId());
        projectsService.deleteById(projectId);
        return "redirect:/projects-info/id/{experienceId}";
    }
}
