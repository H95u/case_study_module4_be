package com.example.cs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class UserPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    private User user;

    @ManyToMany
    private List<Song> songs;
}
