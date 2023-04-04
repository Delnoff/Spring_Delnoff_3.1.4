package com.example.springhtmldelnoff.service;

import com.example.springhtmldelnoff.dao.RoleDao;
import com.example.springhtmldelnoff.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public boolean addRole(Role role) {
        Role userPrimary = roleDao.findByName(role.getRole());
        if (userPrimary != null) {
            return false;
        }
        roleDao.add(role);
        return true;
    }

    @Transactional(readOnly = true)
    public Role findByNameRole(String name) {
        return roleDao.findByName(name);
    }

    @Transactional(readOnly = true)
    public Set<Role> listRoles() {
        return roleDao.listRoles();
    }

}
