package com.cooksys.TwitterMockupAPI.repositories;

import com.cooksys.TwitterMockupAPI.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.TwitterMockupAPI.entities.Hashtag;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    boolean existsByLabel(String label);

    List<Tweet> findByTweetIdAndDeletedFalse(Long id);

    Hashtag findByHashtagId(Long id);

}
