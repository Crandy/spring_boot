package com.zy.study.springboot.web.rest;

import com.zy.study.springboot.domain.Department;
import com.zy.study.springboot.domain.User;
import com.zy.study.springboot.service.RedisService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zy on 17-8-30.
 */

@RestController
public class RedisTestService {

    private static final Logger log = LoggerFactory.getLogger(RedisTestService.class);

    private final RedisService redisService;

    @Autowired
    public RedisTestService(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/save/value")
    public ResponseEntity<Void> saveValue(@RequestParam String key,
                                          @RequestParam Object value) {
        log.info("key: {}, value: {}", key, value);
        redisService.saveValue(key, value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save/user")
    public ResponseEntity<Void> saveUser(@RequestParam String key,
                                          @RequestBody User user) {
        log.info("key: {}, value: {}", key, user);
        redisService.saveValue(key, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/get/user")
    public ResponseEntity<User> getUser(String key) {
        log.info("Get Object form redis | key: {}, value: {}", key);
        User user = redisService.readObject(key, User.class);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save/department")
    public ResponseEntity<Void> saveDepartment(@RequestParam String key,
                                               @RequestBody Department department) {
        log.info("key: {}, value: {}", key, department);
        redisService.saveValue(key, department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/get/department")
    public ResponseEntity<Department> getDepartment(String key) {
        log.info("Get Object form redis | key: {}, value: {}", key);
        Department department = redisService.readObject(key, Department.class);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }


}
