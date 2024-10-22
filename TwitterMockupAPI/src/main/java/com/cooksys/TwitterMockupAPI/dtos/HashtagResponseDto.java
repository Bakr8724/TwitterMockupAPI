package com.cooksys.TwitterMockupAPI.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class HashtagResponseDto {

    private Long id;

    private String Label;

    private Timestamp firstUsed;

    private Timestamp lastUsed;

}

