package com.cooksys.TwitterMockupAPI.services;

import com.cooksys.TwitterMockupAPI.dtos.ContextDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetResponseDto;

public interface TweetService {

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);


    TweetResponseDto getTweetById(Long id);

    TweetResponseDto deleteTweet(Long id);

    ContextDto getTweetContext(Long id);
}
