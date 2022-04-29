package com.ulkhnv.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column
    private Integer score = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "users_likes",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> likedUsers;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "users_dislikes",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> dislikedUsers;
}
