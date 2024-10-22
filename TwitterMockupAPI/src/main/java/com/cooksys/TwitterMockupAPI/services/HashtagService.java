package com.cooksys.TwitterMockupAPI.services;

import com.cooksys.TwitterMockupAPI.dtos.HashtagRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.HashtagResponseDto;
import com.cooksys.TwitterMockupAPI.entities.Hashtag;



public interface HashtagService {

    Hashtag getHashtag(Long id);

    HashtagResponseDto createHashtag(HashtagRequestDto hashtagRequestDto);
}