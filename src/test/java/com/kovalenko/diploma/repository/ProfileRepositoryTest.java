package com.kovalenko.diploma.repository;

import com.kovalenko.diploma.entity.Profile;
import com.kovalenko.diploma.entity.User;
import com.kovalenko.diploma.entity.UserType;
import com.kovalenko.diploma.specification.ProfileSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class ProfileRepositoryTest {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileRepositoryTest(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Test
    void createProfileTest() {
        Profile profile = new Profile("profession1", 5D,
                "This man puts you in mind of a mysterious manta ray. ", 15D,
                47, null, new User("Anton"), new ArrayList<>());
        Profile db = profileRepository.save(profile);

        assertThat(db).isNotNull();
        assertEquals(db.getId(), profile.getId());
        assertThat(db).isEqualTo(profile);
    }

    @Test
    void getAllProfilesTest() {
        List<Profile> profiles = Arrays.asList(
                new Profile("profession1", 5d,
                        "This man puts you in mind of a mysterious manta ray. ", 15d, 97,
                        null, new User("Anton"), new ArrayList<>()),
                new Profile("profession2", 2d,
                        "This lady reminds you of an enraged bear.", 14d, 15,
                        null, new User("Anna"), new ArrayList<>()),
                new Profile("profession3", 2d,
                        "This guy puts you in mind of a darting fish.", 19d, 47,
                        null, new User("Victor"), new ArrayList<>()));

        profiles.forEach(profileRepository::save);
        List<Profile> fromDatabase = profileRepository.findAll();

        assertThat(fromDatabase).isEqualTo(profiles);
    }

    @Test
    void findAllWithSpecificationProfilesShouldReturnAllSortedByRatingAndViewsProfiles() {
        Profile profile1 = new Profile("profession1", 5d,
                "This man puts you in mind of a mysterious manta ray. ", 15d, 97,
                null, new User("Anton", UserType.MENTOR, null, null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)), null);
        Profile profile2 = new Profile("profession2", 2d,
                "This lady reminds you of an enraged bear.", 14d, 15,
                null, new User("Anna", UserType.MENTOR, null, null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)), null);
        Profile profile3 = new Profile("profession3", 2d,
                "This guy puts you in mind of a darting fish.", 19d, 47,
                null, new User("Victor", UserType.MENTOR, null, null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)), null);
        Profile profile4 = new Profile("profession4", 3d,
                "This guy puts you in mind of a darting fish.", 19d, 60,
                null, new User("Petro", UserType.MENTOR, null, null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)), null);
        Profile profile5 = new Profile("profession5", 5d,
                "This man puts you in mind of a mysterious manta ray. ", 11d, 20,
                null, new User("Anton", UserType.MENTOR, null, null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)), null);

        List<Profile> saveProfiles = Arrays.asList(profile1, profile2, profile3, profile4, profile5);

        saveProfiles.forEach(profileRepository::save);

        ProfileSpecification specification = new ProfileSpecification(null);

        List<Profile> expectedProfiles = Arrays.asList(profile1, profile5, profile4, profile3, profile2);
        List<Profile> actualProfiles = profileRepository.findAll(specification);

        assertThat(expectedProfiles).isEqualTo(actualProfiles);
    }

    @Test
    void deleteProfileTest() {
        Profile profile = new Profile("profession1", 5d,
                "This guy puts you in mind of a darting fish.", 19d, 47,
                null, new User("Victor"), new ArrayList<>());

        profileRepository.save(profile);

        profileRepository.deleteById(profile.getId());

        assertThat(profileRepository.findAll())
                .isNotNull()
                .isEmpty();
        assertEquals(0, profileRepository.findAll().size());
    }


    @Test
    void findByIdTest() {
        Profile victorProfile = new Profile("profession1", 5d,
                "This guy puts you in mind of a darting fish.", 19d, 47,
                null, new User("Victor"), new ArrayList<>());
        Profile annaProfile = new Profile("profession1", 5d,
                "This lady reminds you of an enraged bear.", 14d, 47,
                null, new User("Anna"), new ArrayList<>());

        profileRepository.save(victorProfile);
        profileRepository.save(annaProfile);

        assertTrue(profileRepository.findById(victorProfile.getId()).isPresent());
        assertEquals(profileRepository.findById(victorProfile.getId()).get(), victorProfile);

        assertTrue(profileRepository.findById(annaProfile.getId()).isPresent());
        assertEquals(profileRepository.findById(annaProfile.getId()).get(), annaProfile);
    }

    @Test
    void updateUserTest() {
        Profile annaProfile = new Profile("profession1", 5d,
                "This lady reminds you of an enraged bear.", 14d, 47,
                null, new User("Anna"), new ArrayList<>());

        profileRepository.save(annaProfile);

        annaProfile.setRate(10d);

        profileRepository.save(annaProfile);

        assertTrue(profileRepository.findById(annaProfile.getId()).isPresent());
        Profile db = profileRepository.findById(annaProfile.getId()).get();

        assertEquals(10d, db.getRate());
    }
}
