package com.example.springhtmldelnoff.userinit;

import com.example.springhtmldelnoff.model.Role;
import com.example.springhtmldelnoff.model.User;
import com.example.springhtmldelnoff.service.RoleServiceImpl;
import com.example.springhtmldelnoff.service.UserServiceImpl;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataBaseInit {

    private final RoleServiceImpl roleService;
    private final UserServiceImpl userService;

    public DataBaseInit(RoleServiceImpl roleService, UserServiceImpl userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.addRole(role1);
        roleService.addRole(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();

        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("admin", "admin", 23, "admin@gmail.com", roleAdmin);
        User user2 = new User("user", "user",15, "user@gmail.com", roleUser);

        userService.add(user1);
        userService.add(user2);
    }
}
