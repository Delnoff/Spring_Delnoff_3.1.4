package com.example.springhtmldelnoff.service;

import com.example.springhtmldelnoff.dao.UserDao;
import com.example.springhtmldelnoff.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userPrimary = Optional.ofNullable(userDao.findByName(username));
        if (userPrimary.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return userPrimary.get();
    }
}
