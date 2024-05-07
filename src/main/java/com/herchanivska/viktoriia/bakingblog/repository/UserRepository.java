package com.herchanivska.viktoriia.bakingblog.repository;

import com.herchanivska.viktoriia.bakingblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query(value = """
            SELECT f.* FROM users f
            JOIN user_followers uf ON f.id = uf.follower_id
            WHERE uf.user_id = :userId
            """, nativeQuery = true)
    Set<User> findFollowers(Long userId);
}
