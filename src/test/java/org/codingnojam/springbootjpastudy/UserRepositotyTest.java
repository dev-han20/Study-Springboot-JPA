package org.codingnojam.springbootjpastudy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUser() {

        User user;
        user = new User();
        user.setName("ddd");

        Long nowId = userRepository.save(user);
        User now = userRepository.findById(nowId);

        Assertions.assertThat(now.getId()).isEqualTo(user.getId());
        Assertions.assertThat(now.getName()).isEqualTo(user.getName());

    }

}