package com.example.springhtmldelnoff.service;

import com.example.springhtmldelnoff.dao.RoleDao;
import com.example.springhtmldelnoff.dao.UserDao;
import com.example.springhtmldelnoff.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserServiceImpl(RoleDao roleDao, UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public boolean add(User user) {
        User userPrimary = userDao.findByName(user.getUsername());
        if(userPrimary != null) {return false;}
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        userDao.add(user);
        return true;
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    public void update(User user) {
        User userPrimary = findById(user.getId());
        System.out.println(userPrimary);
        System.out.println(user);
        if(!userPrimary.getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
        userDao.update(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String userName) {
        return userDao.findByName(userName);
    }

}
