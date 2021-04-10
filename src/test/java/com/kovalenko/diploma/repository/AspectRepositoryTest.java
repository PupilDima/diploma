package com.kovalenko.diploma.repository;

import com.kovalenko.diploma.entity.LifeAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class AspectRepositoryTest {

    private final AspectRepository aspectRepository;

    @Autowired
    AspectRepositoryTest(AspectRepository aspectRepository) {
        this.aspectRepository = aspectRepository;
    }

    @Test
    void createAspectTest() {
        LifeAspect lifeAspect = new LifeAspect("Mental Health");
        LifeAspect db = aspectRepository.save(lifeAspect);

        assertThat(db).isNotNull();
        assertEquals(db.getId(), lifeAspect.getId());
        assertThat(db).isEqualTo(lifeAspect);
    }

    @Test
    void getAllAspectsTest() {
        List<LifeAspect> lifeAspects = Arrays.asList(
                new LifeAspect("Mental health"),
                new LifeAspect("Physical health"),
                new LifeAspect("Well Being"));

        lifeAspects.forEach(aspectRepository::save);
        List<LifeAspect> fromDatabase = aspectRepository.findAll();

        assertThat(fromDatabase).isEqualTo(lifeAspects);
    }

    @Test
    void deleteAspectTest() {
        LifeAspect lifeAspect = new LifeAspect("Physical health");

        aspectRepository.save(lifeAspect);

        aspectRepository.deleteById(lifeAspect.getId());

        assertEquals(aspectRepository.findAll().size(), 0);
    }


    @Test
    void findByIdTest() {
        LifeAspect wellBeing = new LifeAspect("Well Being");
        LifeAspect mentalHealth = new LifeAspect("Mental health");

        aspectRepository.save(wellBeing);
        aspectRepository.save(mentalHealth);

        assertTrue(aspectRepository.findById(wellBeing.getId()).isPresent());
        assertEquals(aspectRepository.findById(wellBeing.getId()).get(), wellBeing);

        assertTrue(aspectRepository.findById(mentalHealth.getId()).isPresent());
        assertEquals(aspectRepository.findById(mentalHealth.getId()).get(), mentalHealth);
    }

    @Test
    void updateUserTest() {
        LifeAspect wellBeing = new LifeAspect("Well Being");

        aspectRepository.save(wellBeing);

        wellBeing.setName("Well being and Mentall health");

        aspectRepository.save(wellBeing);

        assertTrue(aspectRepository.findById(wellBeing.getId()).isPresent());
        LifeAspect db = aspectRepository.findById(wellBeing.getId()).get();

        assertEquals(db.getName(), "Well being and Mentall health");
    }
}
