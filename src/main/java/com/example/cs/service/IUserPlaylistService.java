package com.example.cs.service;

import com.example.cs.model.UserPlaylist;
import com.example.cs.model.UserPlaylistSongs;
import com.example.cs.model.dto.PlaylistSongsDTO;
import com.example.cs.model.dto.UserPlaylistDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserPlaylistService extends IGeneralService<UserPlaylist, Long> {
    Page<UserPlaylist> findAllByPage(Pageable pageable);

    UserPlaylistDTO getDetailUserPlayList(Long id);

    void addSongToPlaylist(PlaylistSongsDTO playlistSongsDTO);

    void removeSongToPlaylist(Long id);

    List<UserPlaylist> findByUserId(Long userId);

    void deletePlaylist(Long userId);
}
