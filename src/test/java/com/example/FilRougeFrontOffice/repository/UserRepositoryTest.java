package com.example.FilRougeFrontOffice.repository;

import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@WithMockUser(roles = "USER")
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void when_findByuserEmail_then_returnUserByEmail() {
        //given
        UsersEntity user1 = new UsersEntity(
                "Poulain",
                "Baptiste",
                "123",
                "bapt.poulain@outlook.com",
                "none",
                (byte) 0,
                1,
                "Rouen");

        UsersEntity user2 = new UsersEntity(
                "Jean-Philippe",
                "Couhe",
                "123",
                "jp.couhe@outlook.com",
                "none",
                (byte) 0,
                1,
                "Orleans");

        entityManager.persist(user1);
        entityManager.persist(user2);

        Optional<UsersEntity> result = userRepository.findByuserEmail("bapt.poulain@outlook.com");

        assertThat(result.get().getUserId()).isEqualTo(user1.getUserId());
        assertThat(result.get().getUserName()).isEqualTo(user1.getUserName());
        assertThat(result.get().getUserFirstname()).isEqualTo(user1.getUserFirstname());
        assertThat(result.get().getUserEmail()).isEqualTo(user1.getUserEmail());
        assertThat(result.get().getUserCity()).isEqualTo(user1.getUserCity());
    }

    @Test
    void findByUserName() {

    }

    @Test
    void findByUserEmail() {
    }
}