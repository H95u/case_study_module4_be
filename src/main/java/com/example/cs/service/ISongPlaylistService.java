package com.example.cs.service;

import com.example.cs.model.SongPlaylist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISongPlaylistService extends IGeneralService<SongPlaylist, Long>{
    Page<SongPlaylist> findByPage(Pageable pageable);
}
