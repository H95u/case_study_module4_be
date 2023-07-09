package com.example.cs.model.dto;

import com.example.cs.model.Singer;
import com.example.cs.model.Type;
import lombok.Data;

@Data
public class SongDTO {
    private Long id;
    private String name;
    private String img;
    private String mp3;
    private Long listenCount;

    private String lyric;

    private Singer singer;

    private Type type;
}
