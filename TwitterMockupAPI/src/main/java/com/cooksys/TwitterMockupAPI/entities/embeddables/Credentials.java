package com.cooksys.TwitterMockupAPI.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Credentials {

    private String password;
    private String username;
}
