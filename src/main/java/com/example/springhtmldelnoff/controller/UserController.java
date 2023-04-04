package com.example.springhtmldelnoff.controller;

import com.example.springhtmldelnoff.dao.UserDaoImpl;
import com.example.springhtmldelnoff.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;

@Controller
public class UserController {

    private final UserDaoImpl userDao;

    public UserController(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/user")
    public String print(ModelMap model, Principal principal) {
        User userDaoByName = userDao.findByName(principal.getName());
        model.addAttribute("messages", userDaoByName);
        return "user";
    }
}


