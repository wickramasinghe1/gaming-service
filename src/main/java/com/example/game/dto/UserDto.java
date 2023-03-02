package com.example.game.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String password;
    private Boolean isActive;
    private String createDate;
}
