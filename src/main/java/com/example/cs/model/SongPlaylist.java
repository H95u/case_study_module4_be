package com.example.cs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SongPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String thumbnail;

    @ManyToMany
    private List<Song> songs;
}
