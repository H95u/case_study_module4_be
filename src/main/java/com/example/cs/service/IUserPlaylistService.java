package com.example.cs.service;

import com.example.cs.model.UserPlaylist;
import com.example.cs.model.UserPlaylistSongs;
import com.example.cs.model.dto.PlaylistSongsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserPlaylistService extends IGeneralService<UserPlaylist, Long>{
    Page<UserPlaylist> findAllByPage(Pageable pageable);
    void addSongToPlaylist(PlaylistSongsDTO playlistSongsDTO);
    void removeSongToPlaylist(PlaylistSongsDTO playlistSongsDTOs);
}
