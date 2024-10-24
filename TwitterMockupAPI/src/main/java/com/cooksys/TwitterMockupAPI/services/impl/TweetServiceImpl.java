package com.cooksys.TwitterMockupAPI.services.impl;

import com.cooksys.TwitterMockupAPI.entities.Tweet;
import com.cooksys.TwitterMockupAPI.exceptions.NotFoundException;
import com.cooksys.TwitterMockupAPI.mappers.TweetMapper;
import com.cooksys.TwitterMockupAPI.repositories.TweetRepository;
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

    @Override
    public List<TweetResponseDto> getAllTweets() {
        List<Tweet> tweets = tweetRepository.nonDeletedTweets();

        return tweetMapper.entitiesToResponseDtos(tweets);
    }

    @Override
    public TweetResponseDto getTweetById(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);

        if(optionalTweet.isEmpty()){
            throw new NotFoundException("No user found with id: " + id);
        }
        return tweetMapper.entityToResponseDto(optionalTweet.get());
    }

//    @Override
//    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'createTweet'");
//    }
//
//    @Override
//    public TweetResponseDto getTweetById(Long id) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'getTweetById'");
//    }
//
//    @Override
//    public TweetResponseDto deleteTweet(Long id) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'deleteTweet'");
//    }
//
//    @Override
//    public ContextDto getTweetContext(Long id) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'getTweetContext'");
//    }
//
}
