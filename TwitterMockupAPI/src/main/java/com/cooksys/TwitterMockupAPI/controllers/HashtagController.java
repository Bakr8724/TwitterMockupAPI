package com.cooksys.TwitterMockupAPI.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.TwitterMockupAPI.dtos.HashtagDto;
import com.cooksys.TwitterMockupAPI.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hashtags")
@RequiredArgsConstructor
public class HashtagController {

    private HashtagService hashtagService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashtagDto createHashtag(@RequestBody HashtagDto hashtagDto) {
        return hashtagService.createHashtag(hashtagDto);
    }
}
