package com.cooksys.TwitterMockupAPI.entities;

import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name="user_table")
@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private java.sql.Timestamp joined;

    private boolean deleted;

    @Embedded
    private Profile profile;

    @Embedded
    private Credentials credentials;

    @OneToMany(mappedBy = "mentions")
    private List<Tweet> mentionedIn;

    @OneToMany(mappedBy = "likedBy")
    private List<Tweet> likes;

    @OneToMany
    @JoinTable(name = "followers_following", joinColumns = @JoinColumn(name="follower_id"), inverseJoinColumns = @JoinColumn(name="following_id"))
    private List<User> users;

}
