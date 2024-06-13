package com.cvgenerator.cvg.controller;

import com.cvgenerator.cvg.dto.ContactUsDto;
import com.cvgenerator.cvg.service.ContactUsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * ContactController
 * Created On : 5/28/2024 5:31 PM
 **/
@Controller
@RequestMapping("contact-us")
public class ContactUsController {

    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @GetMapping()
    public String openContactUsForm(Model model) {
        model.addAttribute("contactUsDto", new ContactUsDto());
        return "contactus/form_page";
    }

    @PostMapping("/save")
    public String saveContactUs(@ModelAttribute ContactUsDto contactUsDto, RedirectAttributes redirectAttributes) {
        if (contactUsDto.getIsActive() == null) {
            contactUsDto.setIsActive(true);
        } else {
            contactUsDto.setIsActive(false);
        }
        contactUsService.save(contactUsDto);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String openContactUsList(Model model) throws IOException {
        model.addAttribute("contactUsDtoList", contactUsService.findAll());
        return "contactus/list_page";
    }

    @PostMapping("/edit/{id}")
    public String saveContactUs(@PathVariable("id") Integer id) throws IOException {
        ContactUsDto contactUsDto = contactUsService.findById(id);
        contactUsDto.setIsActive(false);
        contactUsService.save(contactUsDto);
        return "redirect:/";
    }
}
