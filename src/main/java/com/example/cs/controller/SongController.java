package com.example.cs.controller;

import com.example.cs.model.Song;
import com.example.cs.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/api/songs")
public class SongController {
    @Autowired
    private SongService songService;
    @Value("${upload-img}")
    private String uploadImg;
    @Value("${upload-mp3}")
    private String uploadMp3;

    @GetMapping
    public ResponseEntity<Page<Song>> findAll(@PageableDefault(value = 10)
                                              Pageable pageable) {
        return new ResponseEntity<>(songService.findByPage(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestPart Song song,
                                    @RequestPart(required = false) MultipartFile image,
                                    @RequestPart(required = false) MultipartFile mp3) throws IOException {
        String imagePath;
        String mp3Path;
        if (image != null) {
            imagePath = image.getOriginalFilename();
            mp3Path = mp3.getOriginalFilename();
            FileCopyUtils.copy(image.getBytes(), new File(uploadImg + imagePath));
            mp3.transferTo(new File(uploadMp3 + mp3Path));
            song.setImg("/img/" + imagePath);
            song.setMp3("/mp3/" + mp3Path);
        } else {
            song.setImg("/image/default.jpg");
        }

        songService.save(song);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestPart Song song,
                                    @RequestPart(required = false) MultipartFile image,
                                    @RequestPart(required = false) MultipartFile mp3) {
        Optional<Song> candyOptional = songService.findOne(id);
        if (candyOptional.isPresent()) {
            String imagePath;
            String mp3Path;
            try {
                if (image != null && !image.isEmpty()) {
                    imagePath = image.getOriginalFilename();
                    mp3Path = mp3.getOriginalFilename();
                    FileCopyUtils.copy(image.getBytes(), new File(uploadImg + imagePath));
                    mp3.transferTo(new File(uploadMp3 + mp3Path));
                    song.setImg("/img/" + imagePath);
                    song.setMp3("/mp3/" + mp3Path);
                } else {
                    song.setImg(candyOptional.get().getImg());
                    song.setMp3(candyOptional.get().getMp3());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            song.setId(id);
            songService.save(song);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Song>> delete(@PathVariable Long id) {
        Optional<Song> candyOptional = songService.findOne(id);
        if (candyOptional.isPresent()) {
            songService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Song>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(songService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Song>> searchByName(@RequestParam(required = false, defaultValue = "") String name,
                                                   @PageableDefault(size = 10)Pageable pageable){
        return new ResponseEntity<>(songService.searchByName(name, pageable), HttpStatus.OK);

    }

    @GetMapping("/leaderboard")
    public ResponseEntity<Page<Song>> sortPriceAsc(@PageableDefault(value = 10, sort = "listenCount", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Song> songs = songService.findByPage(pageable);
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}
