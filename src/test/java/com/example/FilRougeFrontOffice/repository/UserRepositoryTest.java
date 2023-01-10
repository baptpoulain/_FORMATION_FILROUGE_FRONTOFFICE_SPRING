package com.example.FilRougeFrontOffice.repository;

import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.service.FilesStorageService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;


@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;


    @MockBean
    FilesStorageService filesStorageService;


    @Test
    void when_findByUserName_then_returnUser() {
        userRepository.save(new UsersEntity("jp", "userName", "1234", "exemple@exemple.fr", "123.png", (byte) 0, 1, "Paris"));

        Optional<UsersEntity> result = userRepository.findByUserName("jp");

        // then
        assertThat(result.get()).isNotNull();
        }

        @Test
        void when_findByUserName_then_Not_findUser () {
            userRepository.save(new UsersEntity("jp", "userName", "1234", "exemple@exemple.fr", "123.png", (byte) 0, 1, "Paris"));

            Optional<UsersEntity> result = userRepository.findByUserName("marc");

            // then
            assertThat(result).isEmpty();

        }

        @Test
        void when_findByUserEmail_then_returnUser () {
            userRepository.save(new UsersEntity("jp", "userName", "1234", "exemple@exemple.fr", "123.png", (byte) 0, 2, "Paris"));
            Optional<UsersEntity> result = userRepository.findByUserEmail("exemple@exemple.fr");

            // then
            assertThat(result.get()).isNotNull();

        }

        @Test
        void when_findByUserEmail_then_Not_findUser () {
            userRepository.save(new UsersEntity("jp", "userName", "1234", "exemple@exemple.fr", "123.png", (byte) 0, 2, "Paris"));
            Optional<UsersEntity> result = userRepository.findByUserEmail("exemple1@exemple.fr");

            // then
            assertThat(result).isEmpty();

        }
    }