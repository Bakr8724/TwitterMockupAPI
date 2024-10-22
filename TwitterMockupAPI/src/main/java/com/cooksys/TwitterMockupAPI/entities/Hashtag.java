package com.cooksys.TwitterMockupAPI.entities;


import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="hashtags")
@Entity
@NoArgsConstructor
@Data
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Timestamp firstUsed;

    @Column(nullable = false)
    private Timestamp lastUsed;

    @OneToMany(mappedBy="hashtags")
    private List<Tweet> tweets;
}
