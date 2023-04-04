package com.example.springhtmldelnoff.dao;

import com.example.springhtmldelnoff.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    boolean add(Role user);
    Role findByIdRole(Long id);
    Set<Role> listRoles();
    Role findByName(String name);
    Set<Role> listByName(List<String> name);
}
