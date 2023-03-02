package com.example.game.controller;

import com.example.game.dto.GameDto;
import com.example.game.dto.ResponseDto;
import com.example.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/game")
public class GameAPI {

    private GameService service;

    @Autowired
    public GameAPI(GameService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseDto saveGame(@RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                @RequestParam(value = "gameName") String gameName,
                                @RequestParam(value = "gameDate", required = false) String gameDate
    ) {
        GameDto dto = new GameDto();
        dto.setGameName(gameName);
        dto.setGameDate(gameDate);
        dto.setImageFile(imageFile);

        return service.saveGame(dto);
    }

    @PostMapping("/update")
    public ResponseDto updateGame(@RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                  @RequestParam(value = "gameId") Long gameId,
                                  @RequestParam(value = "gameName") String gameName,
                                  @RequestParam(value = "gameDate",  required = false) String gameDate
    ) {
        GameDto dto = new GameDto();
        dto.setGameId(gameId);
        dto.setGameName(gameName);
        dto.setGameDate(gameDate);
        dto.setImageFile(imageFile);

        return service.updateGame(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteByGameId(@PathVariable("id") Long id) {
        return service.deleteByGameId(id);
    }

    @GetMapping("/get-all")
    public ResponseDto getUser() {
        return service.getAll();
    }

}
