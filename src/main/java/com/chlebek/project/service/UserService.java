package com.chlebek.project.service;

import com.chlebek.project.exception.EmailExistsException;
import com.chlebek.project.model.user.User;
import com.chlebek.project.dto.UserRegistrationDto;

public interface UserService {
    User getUserByEmail(String Email);

    User getUserById(Long id);

    User addUser(UserRegistrationDto user) throws EmailExistsException;

    void updateUser(User userUpdate);

    void autoLogin(String email, String password);

    User setUser();

    boolean isLogin();
}
