package com.tousime_alternative.controller;

import com.tousime_alternative.dto.UpdatePasswordDto;
import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.model.User;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
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
    @PostMapping("/updatePassword")
    public ResponseEntity<UserDto> updatePassword(@RequestBody UpdatePasswordDto dto){
        return ResponseEntity.ok(userService.updatePassword(dto));
    }

}
