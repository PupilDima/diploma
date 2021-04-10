package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.entityDetails.UserSecurityDetails;
import com.kovalenko.diploma.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        return new UserSecurityDetails(userService.findByLogin(login));
    }
}
