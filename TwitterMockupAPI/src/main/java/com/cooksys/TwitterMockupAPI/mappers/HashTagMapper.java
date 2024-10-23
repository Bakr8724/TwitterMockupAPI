package com.cooksys.TwitterMockupAPI.mappers;

import org.mapstruct.Mapper;

import com.cooksys.TwitterMockupAPI.dtos.HashtagDto;
import com.cooksys.TwitterMockupAPI.entities.Hashtag;

@Mapper(componentModel= "spring")
public interface HashTagMapper {

    Hashtag dtoToEntity(HashtagDto hashtagDto);

    HashtagDto entityToDto(Hashtag hashtag);
    
}
