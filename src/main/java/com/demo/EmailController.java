package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmailForm")
    public String showEmailForm(Model model) {
        model.addAttribute("emailForm", new EmailForm());
        return "emailForm";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute("emailForm") EmailForm emailForm, Model model) {
        String to = emailForm.getTo();
        String message = emailForm.getMessage();
        String subject = "Test Email";
        String templateName = "emailTemplate"; // Thymeleaf template name without ".html" extension
        emailService.sendEmail(to, subject, message, templateName);
        model.addAttribute("message", "Email sent successfully to " + to);
        return "emailConfirmation";
    }
}

