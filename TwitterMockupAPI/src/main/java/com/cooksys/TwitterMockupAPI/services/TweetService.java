package com.cooksys.TwitterMockupAPI.services;

import com.cooksys.TwitterMockupAPI.dtos.ContextDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetResponseDto;

import java.util.List;

public interface TweetService {

//    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

//    TweetResponseDto deleteTweet(Long id);
//
//    ContextDto getTweetContext(Long id);

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto getTweetById(Long id);
}
