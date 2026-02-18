package com.dinesh.springsecurityjwtdemo.controller;


import com.dinesh.springsecurityjwtdemo.model.Users;
import com.dinesh.springsecurityjwtdemo.service.JWTTokenService;
import com.dinesh.springsecurityjwtdemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenService jwtTokenService;

    @Autowired
    public UserController(UserService userService,AuthenticationManager authenticationManager,JWTTokenService jwtTokenService) {
        this.userService = userService;
        this.authenticationManager=authenticationManager;
        this.jwtTokenService=jwtTokenService;

    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<Users>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Integer id) {

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);

    }
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");

    }

    @PostMapping("/addUser")
    public ResponseEntity<Users> addUser(@RequestBody Users user)
    {
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtTokenService.generateToken(user.getUsername());
        }
        else
            return "Invalid Credentials";


    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id,@RequestBody Users user){
        if(user.getId()!=id) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.NO_CONTENT);
    }


}
