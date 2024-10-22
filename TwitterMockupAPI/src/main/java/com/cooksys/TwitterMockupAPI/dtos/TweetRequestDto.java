package com.cooksys.TwitterMockupAPI.dtos;

import java.util.List;

import com.cooksys.TwitterMockupAPI.entities.Hashtag;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
//The request is what I expect the client to send me
public class TweetRequestDto {

    private String content;

    private Long inReplyToId;

    private Long repostOfId;

    //hashtags
    private List<Hashtag> hashtags;

}
