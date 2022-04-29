package com.ulkhnv.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "likedUsers")
    private List<Quote> likedQuotes;

    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "dislikedUsers")
    private List<Quote> dislikedQuotes;
}
