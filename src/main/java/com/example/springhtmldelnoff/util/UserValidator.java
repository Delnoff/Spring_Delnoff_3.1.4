package com.example.springhtmldelnoff.util;

import com.example.springhtmldelnoff.model.User;
import com.example.springhtmldelnoff.service.UserDetailsImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    private final UserDetailsImpl usDetailsImpl;

    public UserValidator(UserDetailsImpl usDetailsImpl) {
        this.usDetailsImpl = usDetailsImpl;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            usDetailsImpl.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("email", "", "Such mail already exists!");
    }
}
