package com.cooksys.TwitterMockupAPI.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.TwitterMockupAPI.dtos.HashtagDto;
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
    public HashtagDto createHashtag(HashtagDto hashtagRequestDto) {
        return null;
    }
}
