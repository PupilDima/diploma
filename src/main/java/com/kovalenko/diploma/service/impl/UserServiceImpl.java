package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.entity.User;
import com.kovalenko.diploma.repository.UserRepository;
import com.kovalenko.diploma.service.UserService;
import com.kovalenko.diploma.entity.UserType;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String login = ((UserDetails) event.getAuthentication()
                .getPrincipal())
                .getUsername();

        User user = findByLogin(login);
        user.setLastVisitDate(LocalDateTime.now());

        update(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAllByUserType(UserType userType) {
        return userRepository.findAllByUserType(userType);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional()
    public User update(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with such id:%d not found", user.getId())));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with such login: %s not found", login)));
    }

    @Override
    public User findByIdAndUserType(Long userId, UserType userType) {
        return userRepository.findByIdAndUserType(userId, userType)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with such id: %d not exist", userId)));
    }
}
