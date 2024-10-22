package com.cooksys.TwitterMockupAPI.controllers;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.TwitterMockupAPI.dtos.ContextDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetResponseDto;
import com.cooksys.TwitterMockupAPI.services.TweetService;

import lombok.RequiredArgsConstructor;


@RestController //spring converts to JSON, http handles json
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
    //TODO: Implement the endpoints from the documentation

    private final TweetService tweetService;

    @PostMapping
    public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto){
        return tweetService.createTweet(tweetRequestDto);
    }

    @GetMapping
    public ContextDto getTweetContext(@PathVariable Long id){
        return tweetService.getTweetContext(id);
    }

    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable Long id){
        return tweetService.getTweetById(id);
    }

    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweet(@PathVariable Long id){
        return tweetService.deleteTweet(id);
    }
    

}
