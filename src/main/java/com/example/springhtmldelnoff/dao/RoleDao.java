package com.example.springhtmldelnoff.dao;

import com.example.springhtmldelnoff.model.Role;

import java.util.Set;

public interface RoleDao {
    boolean add(Role user);
    Set<Role> listRoles();
    Role findByName(String name);

}
