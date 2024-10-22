package com.cooksys.TwitterMockupAPI.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @OneToMany(mappedBy = "tweet")
    private List<Tweet> tweets;

}
