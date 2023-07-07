package com.example.cs.service.impl;

import com.example.cs.model.Type;
import com.example.cs.repository.ISingerRepository;
import com.example.cs.repository.ITypeRepository;
import com.example.cs.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepository iTypeRepository;

    @Override
    public List<Type> findAll() {
        return iTypeRepository.findAll();
    }

    @Override
    public Optional<Type> findOne(Long aLong) {
        return iTypeRepository.findById(aLong);
    }

    @Override
    public void save(Type type) {
        iTypeRepository.save(type);
    }

    @Override
    public void delete(Long aLong) {
        iTypeRepository.deleteById(aLong);
    }
}
