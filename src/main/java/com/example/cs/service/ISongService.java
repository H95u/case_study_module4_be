package com.example.cs.service;

import com.example.cs.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ISongService extends IGeneralService<Song, Long> {
    Page<Song> findByPage(Pageable pageable);
    Page<Song> searchByName(@Param("name") String name, Pageable pageable);
}
