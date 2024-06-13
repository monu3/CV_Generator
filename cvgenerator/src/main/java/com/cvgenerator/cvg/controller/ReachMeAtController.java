package com.cvgenerator.cvg.controller;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ReachMeAtDto;
import com.cvgenerator.cvg.enums.ContactType;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.service.ReachMeAtService;
import com.cvgenerator.cvg.utils.HtmlToPdfConvertor;
import com.cvgenerator.cvg.validation.ReachMeAtValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReachMeAtController
 * Created On : 5/15/2024 3:52 PM
 **/
@Controller
@RequestMapping("reach-me-at")
public class ReachMeAtController {
    private final ReachMeAtService reachMeAtService;
    private final BasicInformationService basicInformationService;
    private final ReachMeAtValidation reachMeAtValidation;
    private final HtmlToPdfConvertor htmlToPdfConvertor;

    public ReachMeAtController(ReachMeAtService reachMeAtService, BasicInformationService basicInformationService,
                               ReachMeAtValidation reachMeAtValidation, HtmlToPdfConvertor htmlToPdfConvertor) {
        this.reachMeAtService = reachMeAtService;
        this.basicInformationService = basicInformationService;
        this.reachMeAtValidation = reachMeAtValidation;
        this.htmlToPdfConvertor = htmlToPdfConvertor;
    }

    @GetMapping("/open-form")
    public String openReachMeAtFormPage(Integer basicInformationId, Model model) {
        if (!model.containsAttribute("errorMap")) {
            model.addAttribute("errorMap", new HashMap<String, String>());
        }
        model.addAttribute("contactTypeList", ContactType.getContactTypes());
        model.addAttribute("basicInformationId", basicInformationId);
        return "reachmeat/form_page";
    }

    @PostMapping("/save")
    public String saveReachMeAt(@ModelAttribute ReachMeAtDto reachMeAtDto, RedirectAttributes redirectAttributes) {
        Map<String, String> errorMap = reachMeAtValidation.validate(reachMeAtDto);
        if (errorMap.isEmpty()) {
            reachMeAtDto = reachMeAtService.save(reachMeAtDto);
            redirectAttributes.addAttribute("basicInformationId", reachMeAtDto.getBasicInformationId());
            return "redirect:/reach-me-at/id/{basicInformationId}";
        } else {
            redirectAttributes.addAttribute("basicInformationId", reachMeAtDto.getBasicInformationId());
            redirectAttributes.addFlashAttribute("reachMeAtDto", reachMeAtDto);
            redirectAttributes.addFlashAttribute("errorMap", errorMap);
            return "redirect:/reach-me-at/open-form";
        }
    }

    @GetMapping("/id/{basicInformationId}")
    public String findReachMeAtListByBasicInformationId(@PathVariable("basicInformationId") Integer basicInformationId, Model model) throws IOException {
        List<ReachMeAtDto> reachMeAtDtoList = reachMeAtService.findByBasicInformationId(basicInformationId);
        model.addAttribute("reachMeAtDtoList", reachMeAtDtoList);
        StringBuilder fullName = new StringBuilder("Reach Me AT Details of ");
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        fullName.append(basicInformationDto.getFirstName());
        if (basicInformationDto.getMiddleName() != null) {
            fullName.append(" " + basicInformationDto.getMiddleName());
        }
        fullName.append(" ").append(basicInformationDto.getLastName());
        model.addAttribute("fullName", fullName);
        return "reachmeat/list_page";
    }


    @GetMapping("/edit/{reachMeAtId}")
    public String editReachMeAtById(@PathVariable("reachMeAtId") Integer reachMeAtId,
                                    RedirectAttributes redirectAttributes) throws IOException {
        ReachMeAtDto reachMeAtDto = reachMeAtService.findById(reachMeAtId);
        redirectAttributes.addFlashAttribute("reachMeAtDto", reachMeAtDto);
        redirectAttributes.addAttribute("basicInformationID", reachMeAtDto.getBasicInformationId());
        return "redirect:/reach-me-at/open-form";
    }

    @GetMapping("/add-reach-me-at/{basicInformationId}")
    public String addReachMeAtById(@PathVariable("basicInformationId") Integer basicInformationId,
                                   RedirectAttributes redirectAttributes) {
        ReachMeAtDto reachMeAtDto = new ReachMeAtDto();
        reachMeAtDto.setBasicInformationId(basicInformationId);
        redirectAttributes.addFlashAttribute("reachMeAtDto", reachMeAtDto);
        redirectAttributes.addAttribute("basicInformationId", basicInformationId);
        return "redirect:/reach-me-at/open-form";
    }

    @GetMapping("/delete/{reachMeAtId}")
    public String deleteReachMeAtByReachMeAtId(@PathVariable("reachMeAtId") Integer reachMeAtId,
                                               RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addAttribute("basicInformationID",
                reachMeAtService.findById(reachMeAtId).getBasicInformationId());
        reachMeAtService.deleteById(reachMeAtId);
        return "redirect:/reach-me-at/id/{basicInformationID}";
    }

    @GetMapping("/view/{basicInformationId}")
    public String viewReachMeAtById(@PathVariable("basicInformationId") Integer basicInformationId, Model model) throws IOException {
        model.addAttribute("reachMeAtDtoList", reachMeAtService.findByBasicInformationId(basicInformationId));
        model.addAttribute("basicInformationId", basicInformationId);
        StringBuilder message = new StringBuilder("Contact Details of ");
        BasicInformationDto basicInformationDto = basicInformationService.findById(basicInformationId);
        message.append(basicInformationDto.getFirstName());
        if (basicInformationDto.getMiddleName() != null) {
            message.append(" ").append(basicInformationDto.getMiddleName());
        }
        message.append(" ").append(basicInformationDto.getLastName());
        model.addAttribute("message", message);
        return "reachmeat/view_page";
    }

    @GetMapping("/downloadCvInPdf/{basicInformationId}")
    public String downloadCvInPdf(@PathVariable("basicInformationId") Integer basicInformationId) throws IOException {
        htmlToPdfConvertor.generatePdfFromHtml(basicInformationId);
        return "redirect:/reach-me-at/id/{basicInformationId}";
    }
}
