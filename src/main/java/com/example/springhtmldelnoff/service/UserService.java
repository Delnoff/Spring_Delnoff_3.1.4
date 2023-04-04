package com.example.springhtmldelnoff.service;

import com.example.springhtmldelnoff.model.User;

import java.util.List;

public interface UserService {
    boolean add(User user);
    List<User> listUsers();
    void delete(Long id);
    void update(User user);
    User findById(Long id);
    User findByUsername(String userName);
}
