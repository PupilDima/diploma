package com.kovalenko.diploma.repository;

import com.kovalenko.diploma.entity.User;
import com.kovalenko.diploma.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserType(UserType userType);

    Optional<User> findByIdAndUserType(Long id, UserType userType);

    Optional<User> findByLogin(String login);

}
