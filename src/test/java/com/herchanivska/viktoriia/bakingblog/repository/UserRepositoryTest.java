package com.herchanivska.viktoriia.bakingblog.repository;

import com.herchanivska.viktoriia.bakingblog.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findFollowers() {
        Set<User> followers = new HashSet<>();
        followers.add(userRepository.getReferenceById(1L));
        followers.add(userRepository.getReferenceById(2L));
        User user = new User();
        user.setId(4L);
        user.setEmail("some@mail.com");
        user.setUsername("Username");
        user.setPassword("Password1@");
        user.setBirthDate(LocalDate.of(1993, 4, 15));
        user.setFollowers(followers);
        userRepository.save(user);
        Set<User> actual = userRepository.findFollowers(4L);
        assertEquals(followers.size(), actual.size());
        assertTrue(actual.containsAll(followers));
    }
}