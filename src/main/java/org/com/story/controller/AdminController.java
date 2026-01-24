package org.com.story.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/test")
    public String test() {
        System.out.println("Controller auth = "
                + SecurityContextHolder.getContext().getAuthentication());

        return "ADMIN OK";
    }
}
