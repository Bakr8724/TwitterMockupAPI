package com.cooksys.TwitterMockupAPI.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cooksys.TwitterMockupAPI.dtos.HashtagDto;
import com.cooksys.TwitterMockupAPI.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hashtags")
@RequiredArgsConstructor
public class HashtagController {

    private HashtagService hashtagService;


@GetMapping
    public List<HasttagDto> getAllTags(){
    return hashtagService.getAlltags();
}
}
