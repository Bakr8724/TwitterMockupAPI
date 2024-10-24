package com.cooksys.TwitterMockupAPI.repositories;

import com.cooksys.TwitterMockupAPI.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT t FROM Tweet t WHERE t.deleted = false ORDER BY t.posted DESC")
    List<Tweet> nonDeletedTweets();

    Tweet findById(long id);
}
