package com.example.game.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Data
public class GameDto {
    private Long gameId;
    private String gameName;
    private String gameDate;
    private MultipartFile imageFile;
    private Boolean isActive;
    private String createDate;
}
