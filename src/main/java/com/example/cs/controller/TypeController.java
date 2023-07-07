package com.example.cs.controller;

import com.example.cs.model.Singer;
import com.example.cs.model.Type;
import com.example.cs.service.impl.SingerService;
import com.example.cs.service.impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping
    public ResponseEntity<List<Type>> findAll() {
        return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Type type) {
        typeService.save(type);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id,
                                    @RequestBody Type type) {
        Optional<Type> typeOptional = typeService.findOne(id);
        if (typeOptional.isPresent()) {
            type.setId(id);
            typeService.save(type);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Type>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(typeService.findOne(id), HttpStatus.OK);
    }
}
