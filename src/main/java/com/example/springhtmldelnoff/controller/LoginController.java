package com.example.springhtmldelnoff.controller;

import com.example.springhtmldelnoff.model.User;
import com.example.springhtmldelnoff.service.RoleService;
import com.example.springhtmldelnoff.service.UserService;
import com.example.springhtmldelnoff.util.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/page")
public class LoginController {

    private final UserValidator userValidator;
    private final UserService userService;
    private final RoleService roleService;

    public LoginController(UserValidator userValidator, UserService userService, RoleService roleService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.listRoles());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "registration";
        }
        getUserRole(user);
        userService.add(user);
        return "redirect:/page/login";
    }

    public void getUserRole(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.findByNameRole(role.getRole()))
                .collect(Collectors.toSet()));
    }

}
