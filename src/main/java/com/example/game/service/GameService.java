package com.example.game.service;

import com.example.game.dto.GameDto;
import com.example.game.dto.ResponseDto;

public interface GameService {

    ResponseDto saveGame(GameDto dto);
    ResponseDto updateGame(GameDto dto);

    ResponseDto deleteByGameId(Long id);

    ResponseDto getAll();

}
