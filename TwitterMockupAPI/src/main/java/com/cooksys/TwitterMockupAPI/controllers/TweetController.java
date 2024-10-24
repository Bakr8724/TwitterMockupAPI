package com.cooksys.TwitterMockupAPI.controllers;


import com.cooksys.TwitterMockupAPI.dtos.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.TwitterMockupAPI.services.TweetService;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;

    //get tweets
    @GetMapping
    public List<TweetResponseDto> getAllTweets(){
        return tweetService.getAllTweets();
    }

    //get tweet by id
    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable Long id){
        return tweetService.getTweetById(id);
    }



    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> mentionedUsers(@PathVariable Long id){
        return tweetService.mentionedUsers(id);
    }

    @GetMapping("/{id}/replies")
    public List<TweetResponseDto> getTweetReplies(Long id){
        return tweetService.getTweetReplies(id);
    }

}
