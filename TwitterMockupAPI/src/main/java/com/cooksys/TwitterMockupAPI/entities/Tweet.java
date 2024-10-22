package com.cooksys.TwitterMockupAPI.entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "tweets")
@Entity
@NoArgsConstructor
@Data
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

//     @ManyToOne
//     @JoinColumn(name = "user_id", nullable = false)
//     Column(nullable = false)
//     private User author;

    @Column(nullable = false)
    private java.sql.Timestamp posted;

    private Boolean deleted;
    
    private String content;


    //many tweets can reply to one tweet (parent tweet)
    @ManyToOne
    @JoinColumn(name = "in_reply_to")
    private Tweet inReplyTo;


    //inReplyTo: a tweet can have many replies (child tweets)
    @OneToMany(mappedBy="inReplyTo")
    private List<Tweet> replies; 

    //many tweets can repost one tweet (parent tweet)
    @ManyToOne
    @JoinColumn(name = "repost_of")
    private Tweet repostOf;

    //repostOf: One tweet can have many reposts (child tweets)
    @OneToMany(mappedBy="repostOf")
    private List<Tweet> reposts;

    //one tweet can be liked by many users
    @OneToMany
    @JoinTable(name = "user_likedby", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> likedBy;


    // //one tweet can be mentioned by many users
     @OneToMany
     @JoinTable(name = "user_mentions", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
     private List<User> mentions;

    // //one tweet can have many hashtags
    @OneToMany
    @JoinTable(name = "tweet_hashtags", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns= @JoinColumn(name = "hashtag_id"))
    private List<Hashtag> hashtags;

}
