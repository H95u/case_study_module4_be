package com.example.cs.service.impl;

import com.example.cs.model.Song;
import com.example.cs.model.UserPlaylist;
import com.example.cs.model.UserPlaylistSongs;
import com.example.cs.model.dto.PlaylistSongsDTO;
import com.example.cs.model.dto.SongDTO;
import com.example.cs.model.dto.UserPlaylistDTO;
import com.example.cs.repository.ISongRepository;
import com.example.cs.repository.IUserPlaylistRepository;
import com.example.cs.repository.IUserPlaylistSongsRepository;
import com.example.cs.service.IUserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPlaylistService implements IUserPlaylistService {

    @Autowired
    private IUserPlaylistRepository userPlaylistRepository;
    @Autowired
    private IUserPlaylistSongsRepository userPlaylistSongsRepository;
    @Autowired
    private ISongRepository songRepository;

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
    public UserPlaylistDTO getDetailUserPlayList(Long id) {
        UserPlaylist entity = userPlaylistRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        UserPlaylistDTO dto = new UserPlaylistDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setName(entity.getName());

        List<Song> songs = songRepository.findByUserPlayListId(id);
        if (!CollectionUtils.isEmpty(songs)) {
            List<SongDTO> songList = songs.stream()
                    .map(this::toSongDTO)
                    .collect(Collectors.toList());
            dto.setSongs(songList);
        }

        return dto;
    }

    private SongDTO toSongDTO(Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setName(song.getName());
        dto.setImg(song.getImg());
        dto.setLyric(song.getLyric());
        dto.setType(song.getType());
        dto.setSinger(song.getSinger());
        dto.setMp3(song.getMp3());
        return dto;
    }

    @Transactional
    @Override
    public void addSongToPlaylist(PlaylistSongsDTO playlistSong) {
        Long playlistId = playlistSong.getPlaylistId();
        Long songId = playlistSong.getSongId();

        userPlaylistRepository.addSongToPlaylist(playlistId, songId);
    }

    @Override
    public void removeSongToPlaylist(Long id) {
        userPlaylistRepository.deleteSongOfPlaylist(id);
    }

    @Override
    public List<UserPlaylist> findByUserId(Long userId) {
        return userPlaylistRepository.findUserPlaylistByUserId(userId);
    }

    @Override
    public void deletePlaylist(Long userId) {
        userPlaylistRepository.deletePlaylist(userId);
    }
}
