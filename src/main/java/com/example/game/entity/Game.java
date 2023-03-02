package com.example.game.entity;



import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "GAME")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    private String gameName;
    private Date gameDate;
    private Boolean isActive;
    private Date createDate;
    private String imageUrl;
    private String imageFile;

}
