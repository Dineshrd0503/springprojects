package com.dinesh.springsecurityjwtdemo.service;


import com.dinesh.springsecurityjwtdemo.exception.AlienNotFoundException;
import com.dinesh.springsecurityjwtdemo.model.Alien;
import com.dinesh.springsecurityjwtdemo.repository.AlienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlienService {
    private AlienRepository alienRepository;

    @Autowired
    public AlienService(AlienRepository alienRepository) {
        this.alienRepository = alienRepository;
    }

    public List<Alien> getAliens() {
        return alienRepository.findAll();
    }

    public Alien getAlien(int id) {
        return alienRepository.findById(id).
                orElseThrow(()->new AlienNotFoundException("item not found with id "+id));
    }

    public Alien addAlien(Alien alien) {
        return alienRepository.save(alien);
    }

    public Alien updateAlien(Integer id,Alien alien) {
        Alien a=alienRepository.findById(id).
                orElseThrow(()->new AlienNotFoundException("item not found with id "+alien.getId()));
        a.setName(alien.getName());
        a.setTech(alien.getTech());
        return alienRepository.save(a);

    }

    public void deleteAlien(int id) {
        Alien a=alienRepository.findById(id).
                orElseThrow(()->new AlienNotFoundException("item not found with id"+id));
        alienRepository.delete(a);


    }
}