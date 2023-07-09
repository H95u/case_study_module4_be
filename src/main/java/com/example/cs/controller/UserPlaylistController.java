package com.example.cs.controller;

import com.example.cs.model.UserPlaylist;
import com.example.cs.model.dto.PlaylistSongsDTO;
import com.example.cs.model.dto.UserPlaylistDTO;
import com.example.cs.service.IUserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user-playlists")
public class UserPlaylistController {

    @Autowired
    private IUserPlaylistService userPlaylistService;


    @GetMapping
    public ResponseEntity<Page<UserPlaylist>> findAll(@PageableDefault(value = 10) Pageable pageable) {
        return new ResponseEntity<>(userPlaylistService.findAllByPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPlaylistDTO> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(userPlaylistService.getDetailUserPlayList(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserPlaylist userPlaylist) {
        userPlaylistService.save(userPlaylist);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/add-song")
    public ResponseEntity<?> addSongToPlaylist(@RequestBody PlaylistSongsDTO playlistSongsDTO) {
        userPlaylistService.addSongToPlaylist(playlistSongsDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserPlaylist>> update(@PathVariable Long id, @RequestPart UserPlaylist userPlaylist) {
        Optional<UserPlaylist> userPlaylistOptional = userPlaylistService.findOne(id);
        if (userPlaylistOptional.isPresent()) {
            userPlaylist.setId(id);
            userPlaylistService.save(userPlaylist);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<UserPlaylist>> delete(@PathVariable Long id) {
        Optional<UserPlaylist> userPlaylistOptional = userPlaylistService.findOne(id);
        if (userPlaylistOptional.isPresent()) {
            userPlaylistService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/delete-song")
    public ResponseEntity<?> removeSongToPlaylist(@RequestBody PlaylistSongsDTO playlistSongsDTO) {
        userPlaylistService.removeSongToPlaylist(playlistSongsDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserPlaylist>> findByUserId(@PathVariable Long id) {
        List<UserPlaylist> userPlaylists = userPlaylistService.findByUserId(id);
        if (userPlaylists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userPlaylists, HttpStatus.OK);
    }
}
