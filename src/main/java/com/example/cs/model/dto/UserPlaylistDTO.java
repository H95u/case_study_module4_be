package com.example.cs.model.dto;

import com.example.cs.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class UserPlaylistDTO {

    private Long id;
    private String name;

    private Long userId;

    private List<SongDTO> songs;
}
