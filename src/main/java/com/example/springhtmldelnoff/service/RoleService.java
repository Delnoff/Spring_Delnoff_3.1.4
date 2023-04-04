package com.example.springhtmldelnoff.service;


import com.example.springhtmldelnoff.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    boolean addRole(Role role);

    Role findByNameRole(String name);

    Set<Role> listRoles();

    Role findByIdRole(Long id);

    Set<Role> listByRole(List<String> name);
}
