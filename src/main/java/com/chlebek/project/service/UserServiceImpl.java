package com.chlebek.project.service;

import com.chlebek.project.exception.EmailExistsException;
import com.chlebek.project.model.user.User;
import com.chlebek.project.repository.RoleRepository;
import com.chlebek.project.repository.UserRepository;
import com.chlebek.project.util.DateUtils;
import dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager auth;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User addUser(UserRegistrationDto user) throws EmailExistsException {
        if (checkEmailExists(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + user.getEmail());
        } else {
            User userObject = new User();
            userObject.setEmail(user.getEmail());
            userObject.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userObject.setFirstName(user.getFirstName());
            userObject.setLastName(user.getLastName());
            userObject.setPhoneNumber(user.getPhoneNumber());
            userObject.setAddress(user.getAddress());
            userObject.setJoinedDate(DateUtils.todayStr());
            long customerRoleId = 1;
            userObject.getRoles().add(roleRepository.getRoleById(customerRoleId));
            userRepository.save(userObject);
            return userObject;
        }
    }



    @Override
    public void updateUser(User userUpdate) {
            userRepository.save(userUpdate);
    }

    private boolean checkEmailExists(String email) {
        User user = null;
        user = userRepository.findByEmail(email);

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void autoLogin(String email, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        auth.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    public User setUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return getUserByEmail(userDetails.getUsername());
    }
}
