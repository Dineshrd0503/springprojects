package com.dinesh.springsecurityjwtdemo.controller;

import com.dinesh.springsecurityjwtdemo.model.Alien;
import com.dinesh.springsecurityjwtdemo.service.AlienService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aliens")
public class AlienController {

    private AlienService alienService;

    @Autowired
    public AlienController(AlienService alienService) {
        this.alienService = alienService;
    }

    @GetMapping("/allAliens")
    public ResponseEntity<List<Alien>> getAliens() {
        return new ResponseEntity<>(alienService.getAliens(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alien> getAlien(@PathVariable int id) {

        return new ResponseEntity<>(alienService.getAlien(id), HttpStatus.OK);
    }

    @PostMapping("/addAlien")
    public ResponseEntity<Alien> addAlien(@RequestBody Alien alien) {
        return new ResponseEntity<>(alienService.addAlien(alien), HttpStatus.CREATED);
    }

    @PutMapping("/updateAlien/{id}")
    public ResponseEntity<Alien> updateAlien(@PathVariable int id, @RequestBody Alien alien) {
        // Ensure the ID in the path matches the ID in the body
        if (alien.getId()!= id) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(alienService.updateAlien(id,alien), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAlien/{id}")
    public ResponseEntity<Void> deleteAlien(@PathVariable int id) {
        alienService.deleteAlien(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");

    }


}