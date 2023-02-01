package com.domanski.movieclub.web;

import com.domanski.movieclub.domain.user.Dto.UserCredentialsDto;
import com.domanski.movieclub.domain.user.Dto.UserEditDto;
import com.domanski.movieclub.domain.user.UserService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/uzytkonicy")
    public String getUserInfo(Model model, @CurrentSecurityContext SecurityContext securityContext) {
        String userName = securityContext.getAuthentication().getName();
        UserEditDto user = userService.findUserByEmail(userName).map(this::map).orElseThrow();
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("uzytkownicy")
    public String editUserInfo(Model model, UserEditDto user) {
        userService.editByUser(user);
        return "redirect:/logout";
    }

    private UserEditDto map(UserCredentialsDto user) {

        return new UserEditDto(user.getEmail(),
                user.getEmail(),
                user.getPassword(),
                user.getPassword());
    }
}

