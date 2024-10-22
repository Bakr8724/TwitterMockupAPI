package com.cooksys.TwitterMockupAPI.entities;

import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name="tweet_user")
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    //private String username;

    //private String password;

    private java.sql.Timestamp joined;

    private boolean deleted;

//    private String firstName;
//
//    private String lastName;
//
//    private String email;
//
//    private String phone;

    @Embedded
    private Profile profile;

    @Embedded
    private Credentials credentials;

//  @OneToMany(mappedBy = "tweet")
//   private List<Tweet> tweets;

    @OneToMany
    @JoinTable(name = "followers_following", joinColumns = @JoinColumn(name="follower_id"), inverseJoinColumns = @JoinColumn(name="following_id"))
    private List<User> users;

//    @ManyToOne
//    @JoinTable

//    @OneToMany
//    @JoinTable(name = "user_likes", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="tweet_id"))
//    private List<Tweet> likes;
//
//    @OneToMany
//    @JoinTable(name = "user_mentions", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="tweet_id"))
//    private List<Tweet> mentions;
}
