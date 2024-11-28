package com.akhilp.RBAC.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/user")
    public String greet() {
        return "hello User ";
    }

    @GetMapping("/moderator")
    public String greetmoderator() {
        return "hello Moderator ";
    }

    @GetMapping("/admin")
    public String greetadmin() {
        return "hello Admin ";
    }
}
