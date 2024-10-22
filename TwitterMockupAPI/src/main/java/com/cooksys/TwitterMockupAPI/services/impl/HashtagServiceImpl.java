package com.cooksys.TwitterMockupAPI.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.TwitterMockupAPI.dtos.HashtagRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.HashtagResponseDto;
import com.cooksys.TwitterMockupAPI.entities.Hashtag;
import com.cooksys.TwitterMockupAPI.services.HashtagService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
    @Override
    public Hashtag getHashtag(Long id) {
        return null;
    }

    @Override
    public HashtagResponseDto createHashtag(HashtagRequestDto hashtagRequestDto) {
        return null;
    }
}
