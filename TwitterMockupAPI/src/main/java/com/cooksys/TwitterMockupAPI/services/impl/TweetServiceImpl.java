package com.cooksys.TwitterMockupAPI.services.impl;

import com.cooksys.TwitterMockupAPI.dtos.TweetRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.UserResponseDto;
import com.cooksys.TwitterMockupAPI.entities.Tweet;
import com.cooksys.TwitterMockupAPI.entities.User;
import com.cooksys.TwitterMockupAPI.exceptions.NotFoundException;
import com.cooksys.TwitterMockupAPI.mappers.TweetMapper;
import com.cooksys.TwitterMockupAPI.mappers.UserMapper;
import com.cooksys.TwitterMockupAPI.repositories.TweetRepository;
import com.cooksys.TwitterMockupAPI.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.cooksys.TwitterMockupAPI.dtos.TweetResponseDto;
import com.cooksys.TwitterMockupAPI.services.TweetService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TweetServiceImpl implements TweetService {

    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;
    private UserRepository userRepository;
    private UserMapper userMapper;

    private Tweet tweetId(Long id){
        Optional<Tweet> optionalTweet = tweetRepository.nonDeletedTweetsByID(id);

        if(optionalTweet.isEmpty()){
            throw new NotFoundException("Tweet not found or deleted with id: " + id);
        }

        return optionalTweet.get();
    }

    @Override
    public List<TweetResponseDto> getAllTweets() {
        List<Tweet> tweets = tweetRepository.nonDeletedTweets();

        return tweetMapper.entitiesToResponseDtos(tweets);
    }

    @Override
    public TweetResponseDto getTweetById(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("No user found with id: " + id);
        }
        return tweetMapper.entityToResponseDto(optionalTweet.get());
    }

    @Override
    public List<UserResponseDto> mentionedUsers(Long id) {
        Tweet tweet = tweetId(id);

        List<User> users = userRepository.findMentions(id);

        if (users.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }

        return userMapper.entitiesToResponseDtos(users);
    }
}
