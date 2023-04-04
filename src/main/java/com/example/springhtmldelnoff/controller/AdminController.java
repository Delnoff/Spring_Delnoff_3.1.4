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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String users(Model model, Principal principal) {
        model.addAttribute("messages", userService.findByUsername(principal.getName()));
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roleList", roleService.listRoles());
        return "admin";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/admin";
        }

        getUserRole(user);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        getUserRole(user);
        userService.update(user);
        return "redirect:/admin";
    }

    public void getUserRole(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.findByNameRole(role.getRole()))
                .collect(Collectors.toSet()));
    }
}
