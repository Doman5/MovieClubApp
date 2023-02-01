package com.domanski.movieclub.web.admin;

import com.domanski.movieclub.domain.user.Dto.UserEditByAdminDto;
import com.domanski.movieclub.domain.user.Dto.UserInfoDto;
import com.domanski.movieclub.domain.user.Dto.UserRoleDto;
import com.domanski.movieclub.domain.user.UserRoleService;
import com.domanski.movieclub.domain.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminUserController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    public AdminUserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/admin/uzytkownicy")
    public String showAllUsersInfo(Model model) {
        UserEditByAdminDto user = new UserEditByAdminDto();
        model.addAttribute("user", user);
        List<UserInfoDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        List<UserRoleDto> roles = userRoleService.findAllUserRole();
        model.addAttribute("roles", roles);
        return "admin/users-list";
    }

    @PostMapping("/admin/uzytkownicy")
    public String editUser(UserEditByAdminDto user, RedirectAttributes redirectAttributes) {
        userService.editByAdmin(user);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Użytkownik %s został edytowany".formatted(user.getEmail())
        );
        return "redirect:/admin";
    }
}
