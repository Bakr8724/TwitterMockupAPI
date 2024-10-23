package com.cooksys.TwitterMockupAPI.services;

import com.cooksys.TwitterMockupAPI.dtos.HashtagDto;
import com.cooksys.TwitterMockupAPI.entities.Hashtag;



public interface HashtagService {

    Hashtag getHashtag(Long id);

    HashtagDto createHashtag(HashtagDto hashtagRequestDto);
}