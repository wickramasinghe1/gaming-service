package com.example.game.controller;

import com.example.game.dto.ResponseDto;
import com.example.game.dto.UserDto;
import com.example.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserAPI {
    private UserService service;

    @Autowired
    public UserAPI(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserDto dto) {
        return service.login(dto);
    }
}
