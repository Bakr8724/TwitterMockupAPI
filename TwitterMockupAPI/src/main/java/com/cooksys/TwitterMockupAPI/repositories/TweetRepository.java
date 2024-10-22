package com.cooksys.TwitterMockupAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.TwitterMockupAPI.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    
}
