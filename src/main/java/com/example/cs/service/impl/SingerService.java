package com.example.cs.service.impl;

import com.example.cs.model.Singer;
import com.example.cs.repository.ISingerRepository;
import com.example.cs.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService implements ISingerService {
    @Autowired
    private ISingerRepository iSingerRepository;

    @Override
    public List<Singer> findAll() {
        return iSingerRepository.findAll();
    }

    @Override
    public Optional<Singer> findOne(Long aLong) {
        return iSingerRepository.findById(aLong);
    }

    @Override
    public void save(Singer singer) {
        iSingerRepository.save(singer);
    }

    @Override
    public void delete(Long aLong) {
        iSingerRepository.deleteById(aLong);
    }


}
