package com.kovalenko.diploma.repository;

import com.kovalenko.diploma.entity.Contact;
import com.kovalenko.diploma.entity.User;
import com.kovalenko.diploma.entity.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class UserRepositoryTest {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void createUserTest() {
        User john = new User("John Smith", UserType.MENTOR,
                new Contact("johnsmt@gmail.com", "3587347"), null, "password", "login",
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER));
        User db = userRepository.save(john);

        assertThat(db).isNotNull();
        assertEquals(db.getId(), john.getId());
        assertThat(db).isEqualTo(john);
    }

    @Test
    void getAllUsersTest() {
        List<User> users = Arrays.asList(
                new User("John Smith", UserType.MENTOR,
                        new Contact("johnsmt@gmail.com", "3587347"), null, "password",
                        "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                        LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)),
                new User("Ember Lane", UserType.MENTOR,
                        new Contact("emberL@gmail.com", "3580981"), null, "password",
                        "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                        LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)),
                new User("Thomas Newman", UserType.MENTOR,
                        new Contact("thomasNew@gmail.com", "3447349"), null, "password",
                        "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                        LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)),
                new User("Hans Zimmer", UserType.MENTOR,
                        new Contact("hanszimm@gmail.com", "3549350"), null, "password",
                        "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                        LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER)));

        users.forEach(userRepository::save);
        List<User> fromDatabase = userRepository.findAll();

        assertThat(fromDatabase).isEqualTo(users);
    }

    @Test
    void deleteUserTest() {
        User john = new User("John Smith", UserType.MENTOR,
                new Contact("johnsmt@gmail.com", "3587347"), null, "password", "login",
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER));
        User ember = new User("Ember Lane", UserType.MENTOR,
                new Contact("emberL@gmail.com", "3580981"), null, "password", "login",
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER));

        userRepository.save(john);
        userRepository.save(ember);

        userRepository.deleteById(john.getId());

        assertThat(userRepository.findAll()).isNotNull();
        assertThat(userRepository.findAll()).isNotEmpty();
        assertEquals(userRepository.findAll().size(), 1);
    }


    @Test
    void findByIdTest() {
        User john = new User("John Smith", UserType.MENTOR,
                new Contact("johnsmt@gmail.com", "3587347"), null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER));
        User ember = new User("Ember Lane", UserType.MENTOR,
                new Contact("emberL@gmail.com", "3580981"), null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER));

        userRepository.save(john);
        userRepository.save(ember);

        assertTrue(userRepository.findById(john.getId()).isPresent());
        assertEquals(userRepository.findById(john.getId()).get(), john);

        assertTrue(userRepository.findById(ember.getId()).isPresent());
        assertEquals(userRepository.findById(ember.getId()).get(), ember);
    }

    @Test
    void updateUserTest() {
        User john = new User("John Smith", UserType.MENTOR,
                new Contact("johnsmt@gmail.com", "3587347"), null, "password",
                "login", LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2021-01-21 12:30", DATE_TIME_FORMATTER));

        userRepository.save(john);

        john.setUserType(UserType.CLIENT);

        userRepository.save(john);

        assertTrue(userRepository.findById(john.getId()).isPresent());
        User db = userRepository.findById(john.getId()).get();

        assertEquals(db.getUserType(), UserType.CLIENT);
    }
}
