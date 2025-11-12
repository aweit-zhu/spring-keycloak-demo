package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello from Public API!";
    }

    @GetMapping("/secure/hello")
    public String secureHello(@AuthenticationPrincipal Jwt jwt) {
        return "Hello, " + jwt.getClaim("preferred_username") + "!";
    }
}