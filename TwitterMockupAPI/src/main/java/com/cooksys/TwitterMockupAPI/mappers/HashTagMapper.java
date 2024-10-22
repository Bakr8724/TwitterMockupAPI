package com.cooksys.TwitterMockupAPI.mappers;

import org.mapstruct.Mapper;

import com.cooksys.TwitterMockupAPI.dtos.HashtagRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.HashtagResponseDto;
import com.cooksys.TwitterMockupAPI.entities.Hashtag;

@Mapper(componentModel= "spring")
public interface HashTagMapper {

    Hashtag requestDtoEntity(HashtagRequestDto hashtagRequestDto);

    HashtagResponseDto entityToResponseDto(Hashtag hashtag);
    
}
