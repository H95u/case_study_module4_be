package com.example.cs.service.impl;

import com.example.cs.model.UserPlaylist;
import com.example.cs.model.UserPlaylistSongs;
import com.example.cs.model.dto.PlaylistSongsDTO;
import com.example.cs.repository.IUserPlaylistRepository;
import com.example.cs.repository.IUserPlaylistSongsRepository;
import com.example.cs.service.IUserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPlaylistService implements IUserPlaylistService {

    @Autowired
    private IUserPlaylistRepository userPlaylistRepository;
    @Autowired
    private IUserPlaylistSongsRepository userPlaylistSongsRepository;
    @Override
    public List<UserPlaylist> findAll() {
        return userPlaylistRepository.findAll();
    }

    @Override
    public Optional<UserPlaylist> findOne(Long aLong) {
        return userPlaylistRepository.findById(aLong);
    }

    @Override
    public void save(UserPlaylist userPlaylist) {
        userPlaylistRepository.save(userPlaylist);
    }

    @Override
    public void delete(Long aLong) {
        userPlaylistRepository.deleteById(aLong);
    }

    @Override
    public Page<UserPlaylist> findAllByPage(Pageable pageable) {
        return userPlaylistRepository.findAll(pageable);
    }

    @Override
    public void addSongToPlaylist(PlaylistSongsDTO playlistSong) {
        UserPlaylistSongs entity = new UserPlaylistSongs();
        entity.setUserPlaylistId(playlistSong.getPlaylistId());
        entity.setSongsId(playlistSong.getSongId());
        userPlaylistSongsRepository.save(entity);
    }

    @Override
    public void removeSongToPlaylist(PlaylistSongsDTO playlistSong) {
        Optional<UserPlaylistSongs> entity = userPlaylistSongsRepository.findByUserPlaylistIdAndSongsId(
                playlistSong.getPlaylistId(), playlistSong.getSongId());
        if (entity.isPresent()) {
            userPlaylistSongsRepository.delete(entity.get());
        }
    }
}
