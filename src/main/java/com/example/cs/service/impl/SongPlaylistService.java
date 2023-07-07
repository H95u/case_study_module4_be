package com.example.cs.service.impl;

import com.example.cs.model.SongPlaylist;
import com.example.cs.repository.ISongPlaylistRepository;
import com.example.cs.service.ISongPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongPlaylistService implements ISongPlaylistService {

    @Autowired
    ISongPlaylistRepository songPlaylistRepository;
    @Override
    public List<SongPlaylist> findAll() {
        return songPlaylistRepository.findAll();
    }

    @Override
    public Optional<SongPlaylist> findOne(Long aLong) {
        return songPlaylistRepository.findById(aLong);
    }

    @Override
    public void save(SongPlaylist songPlaylist) {
        songPlaylistRepository.save(songPlaylist);
    }

    @Override
    public void delete(Long aLong) {
        songPlaylistRepository.deleteById(aLong);
    }

    @Override
    public Page<SongPlaylist> findByPage(Pageable pageable) {
        return songPlaylistRepository.findAll(pageable);
    }
}
