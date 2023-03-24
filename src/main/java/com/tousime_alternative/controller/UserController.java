package com.tousime_alternative.controller;

import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userservice) {
        this.userService = userservice;
    }

    @GetMapping("/all")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/delete/{idUser}")
    public void delete(@PathVariable("idUser") Long id) {
        userService.delete(id);
    }
}