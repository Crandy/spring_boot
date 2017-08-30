package com.zy.study.springboot.web.rest;

import com.zy.study.springboot.domain.User;
import com.zy.study.springboot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zy on 17-8-30.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserRepository userRepository;

    @Autowired
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/create/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User userCreated = userRepository.save(user);
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }
}
