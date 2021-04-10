package com.kovalenko.diploma.service.impl;

import com.kovalenko.diploma.service.ProfileService;
import com.kovalenko.diploma.specification.ProfileSpecification;
import com.kovalenko.diploma.entity.Profile;
import com.kovalenko.diploma.entity.UserType;
import com.kovalenko.diploma.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, EntityManager entityManager) {
        this.profileRepository = profileRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Profile findByUserIdAndUserType(Long id, UserType userType) {
        return profileRepository.findByUserIdAndUserUserType(id, userType)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Profile with such id: %d not exist", id)));
    }

    @Override
    public List<Profile> findAll(ProfileSpecification specification) {
        return profileRepository.findAll(specification);
    }

    @Override
    @Transactional
    public Profile update(Profile profile, Long profileId) {
        return entityManager.merge(profile);
    }
}
