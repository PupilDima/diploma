package com.kovalenko.diploma.repository;

import com.kovalenko.diploma.entity.Profile;
import com.kovalenko.diploma.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {
    Optional<Profile> findByUserIdAndUserUserType(Long id, UserType userType);

    List<Profile> findAllByUserUserType(UserType userType);
}
