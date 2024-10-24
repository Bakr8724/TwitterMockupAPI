package com.cooksys.TwitterMockupAPI.repositories;

import com.cooksys.TwitterMockupAPI.entities.User;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByCredentials(Credentials credentials);

    Optional<User> findByCredentialsUsername(String username);

    @Query("Select u from User u where u.deleted = false")
    List<User> getActiveUsers();

    boolean existsByCredentialsUsername(String username);
}
