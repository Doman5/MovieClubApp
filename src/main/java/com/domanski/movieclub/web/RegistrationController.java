package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.user.Dto.UserRegistrationDto;
import com.domanski.movieclub.domain.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rejestracja")
    public String getRegistrationForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration-form";
    }

    @PostMapping("/rejestracja")
    public String saveUserFromForm(UserRegistrationDto userRegistrationDto) {
        userService.RegisterUser(userRegistrationDto);
        return "redirect:/";
    }
}
