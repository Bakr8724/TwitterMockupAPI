package com.cooksys.TwitterMockupAPI.dtos;


import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {

    private Long id;

    private String content;

    // private UserResponseDto author;

    private java.sql.Timestamp posted;

    private Boolean deleted;

    private List<TweetResponseDto> replies;

    private List<TweetResponseDto> reposts;

    private TweetResponseDto inReplyTo;

    private TweetResponseDto repostOf;

    // private List<UserResponseDto> likes;

    // private List<UserResponseDto> mentions;

    // private List<HashTagResponseDto> tweet_HashTags;

}
