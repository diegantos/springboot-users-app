package com.ds.backend.usersapp.backendusersapp.controllers;

import com.ds.backend.usersapp.backendusersapp.models.entities.User;
import com.ds.backend.usersapp.backendusersapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    @GetMapping
    public List<User> list(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow()); //ok devuelve un 200
        }
        return ResponseEntity.notFound().build(); // notFound devuelve 404
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id){
        Optional<User> o = service.update(user, id);
        if (o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<User> o = service.findById(id);
        if (o.isPresent()){
            service.remove(id);
            return ResponseEntity.noContent().build(); // noContent devuelve un 204
        }
        return ResponseEntity.notFound().build();
    }
}
