package com.cooksys.TwitterMockupAPI.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.TwitterMockupAPI.dtos.HashtagRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.HashtagResponseDto;
import com.cooksys.TwitterMockupAPI.services.HashtagService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/hashtags")
@AllArgsConstructor
public class HashtagController {

    private HashtagService hashtagService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashtagResponseDto createHashtag(@RequestBody HashtagRequestDto hashtagRequestDto) {
        return hashtagService.createHashtag(hashtagRequestDto);
    }
}
