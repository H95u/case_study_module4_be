package com.example.cs.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    private String mp3;
    private Long listenCount;
    @Column(columnDefinition = "LONGTEXT",nullable = true)
    private String lyric;
    @OneToOne
    private Singer singer;

    @OneToOne
    private Type type;

}
