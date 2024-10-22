package com.cooksys.TwitterMockupAPI.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.TwitterMockupAPI.dtos.ContextDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetResponseDto;
import com.cooksys.TwitterMockupAPI.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    
    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTweet'");
    }

    @Override
    public TweetResponseDto getTweetById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTweetById'");
    }

    @Override
    public TweetResponseDto deleteTweet(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTweet'");
    }

    @Override
    public ContextDto getTweetContext(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTweetContext'");
    }
    
}
