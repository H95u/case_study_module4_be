package com.example.cs.controller;

import com.example.cs.model.Singer;
import com.example.cs.service.impl.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/singers")
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping
    public ResponseEntity<List<Singer>> findAll() {
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Singer singer) {
        singerService.save(singer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id,
                                    @RequestBody Singer singer) {
        Optional<Singer> singerOptional = singerService.findOne(id);
        if (singerOptional.isPresent()) {
            singer.setId(id);
            singerService.save(singer);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Singer>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(singerService.findOne(id), HttpStatus.OK);
    }

}
