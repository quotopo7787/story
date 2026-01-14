package org.com.story.controller;

import lombok.RequiredArgsConstructor;
import org.com.story.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/mail")
@RequiredArgsConstructor
public class MailTestController {

    private final MailService mailService;

    @GetMapping
    public String testMail() {
        mailService.sendTextMail(
                "quangtn7787@gmail.com",
                "Test mail",
                "Mail sent successfully"
        );
        return "OK";
    }
}