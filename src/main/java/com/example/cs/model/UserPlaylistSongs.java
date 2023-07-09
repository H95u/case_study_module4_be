package com.example.cs.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserPlaylistSongs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userPlaylistId;
    private Long songsId;
}
