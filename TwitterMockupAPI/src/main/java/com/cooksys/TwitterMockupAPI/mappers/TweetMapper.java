package com.cooksys.TwitterMockupAPI.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.TwitterMockupAPI.dtos.TweetRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetResponseDto;
import com.cooksys.TwitterMockupAPI.entities.Tweet;

@Mapper(componentModel= "spring", uses = {UserMapper.class})
public interface TweetMapper {
    Tweet requestDtoToEntity(TweetRequestDto tweetRequestDto);

    TweetResponseDto entityToResponseDto(Tweet tweet);

    List<TweetResponseDto> entitiesToResponseDtos(List<Tweet> tweets);
}
