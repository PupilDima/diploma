package com.kovalenko.diploma.service;

import com.kovalenko.diploma.entity.User;
import com.kovalenko.diploma.entity.UserType;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import java.util.List;
import java.util.Optional;

public interface UserService extends ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    void onApplicationEvent(AuthenticationSuccessEvent event);

    Optional<User> findById(Long userId);

    List<User> findAllByUserType(UserType userType);

    User save(User user);

    User update(User user);

    void deleteById(Long userId);

    User findByIdAndUserType(Long userId, UserType userType);

    User findByLogin(String login);
}
