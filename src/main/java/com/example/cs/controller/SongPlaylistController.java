package com.example.cs.controller;

import com.example.cs.model.SongPlaylist;
import com.example.cs.service.ISongPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/song-playlists")
public class SongPlaylistController {

    @Autowired
    ISongPlaylistService songPlaylistService;
    @Value("${upload-img}")
    String thumbnail;

    @GetMapping
    public ResponseEntity<Page<SongPlaylist>> finAll(@PageableDefault(value = 8)
                                                     Pageable pageable) {
        return new ResponseEntity<>(songPlaylistService.findByPage(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<SongPlaylist>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(songPlaylistService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestPart SongPlaylist songPlaylist,
                                    @RequestPart(required = false) MultipartFile image) throws IOException {
        String imagePath;
        if (image != null) {
            imagePath = image.getOriginalFilename();
            FileCopyUtils.copy(image.getBytes(), new File(thumbnail + imagePath));
            songPlaylist.setThumbnail("/img/" + imagePath);
        } else {
            songPlaylist.setThumbnail("/image/default.jpg");
        }
        songPlaylistService.save(songPlaylist);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestPart SongPlaylist songPlaylist,
                                    @RequestPart(required = false) MultipartFile image) {
        Optional<SongPlaylist> songPlaylistOptional = songPlaylistService.findOne(id);
        if (songPlaylistOptional.isPresent()) {
            String imagePath;
            try {
                if (image != null && image.isEmpty()) {
                    imagePath = image.getOriginalFilename();
                    FileCopyUtils.copy(image.getBytes(), new File(thumbnail + imagePath));
                    songPlaylist.setThumbnail("/img/" + imagePath);
                } else {
                    songPlaylist.setThumbnail(songPlaylistOptional.get().getThumbnail());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            songPlaylist.setId(id);
            songPlaylistService.save(songPlaylist);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
