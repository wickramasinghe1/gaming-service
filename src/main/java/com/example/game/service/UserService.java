package com.example.game.service;

import com.example.game.dto.ResponseDto;
import com.example.game.dto.UserDto;

public interface UserService {
    ResponseDto login(UserDto dto);
}
