package com.cooksys.TwitterMockupAPI.services;

import com.cooksys.TwitterMockupAPI.dtos.*;

import java.util.List;

public interface TweetService {

//    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

//    TweetResponseDto deleteTweet(Long id);
//
//    ContextDto getTweetContext(Long id);

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto getTweetById(Long id);

    List<UserResponseDto> mentionedUsers(Long id);

    List<TweetResponseDto> getTweetReplies(Long id);

}
