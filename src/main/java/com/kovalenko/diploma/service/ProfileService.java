package com.kovalenko.diploma.service;

import com.kovalenko.diploma.entity.Profile;
import com.kovalenko.diploma.entity.UserType;
import com.kovalenko.diploma.specification.ProfileSpecification;

import java.util.List;

public interface ProfileService {
    Profile findByUserIdAndUserType(Long id, UserType userType);

    List<Profile> findAll(ProfileSpecification specification);

    Profile update(Profile profile, Long profileId);
}
