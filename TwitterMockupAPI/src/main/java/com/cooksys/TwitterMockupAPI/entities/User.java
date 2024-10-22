package com.cooksys.TwitterMockupAPI.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name="tweet_user")
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private LocalDateTime joined;

    private boolean deleted;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

//    @OneToMany(mappedBy = "tweet")
//    private List<Tweet> tweets;

}
