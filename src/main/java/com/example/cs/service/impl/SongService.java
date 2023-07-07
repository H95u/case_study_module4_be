package com.example.cs.service.impl;

import com.example.cs.model.Song;
import com.example.cs.repository.ISongRepository;
import com.example.cs.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository iSongRepository;

    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public Optional<Song> findOne(Long aLong) {
        return iSongRepository.findById(aLong);
    }

    @Override
    public void save(Song song) {
        iSongRepository.save(song);
    }

    @Override
    public void delete(Long aLong) {
        iSongRepository.deleteById(aLong);
    }

    @Override
    public Page<Song> findByPage(Pageable pageable) {
        return iSongRepository.findAll(pageable);
    }

    @Override
    public Page<Song> searchByName(String name, Pageable pageable) {
        return iSongRepository.searchByName(name, pageable);
    }
}
