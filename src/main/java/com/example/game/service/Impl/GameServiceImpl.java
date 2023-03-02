package com.example.game.service.Impl;

import com.example.game.constants.AppConstants;
import com.example.game.dto.GameDto;
import com.example.game.dto.ResponseDto;
import com.example.game.entity.Game;
import com.example.game.repository.GameRepository;
import com.example.game.service.GameService;
import com.example.game.util.FileUtilizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Value("${archive.path}")
    private String archivePath;
    @Value("${backendBaseUrl}")
    private String baseUrl;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public ResponseDto saveGame(GameDto dto) {
        try {
            if (dto.getGameName() == null || dto.getGameDate() == null) {
                return new ResponseDto(null, AppConstants.CODE_500, "Something is wrong!");
            }

            Game game = new Game();
            game.setGameId(0L);
            game.setIsActive(true);
            game.setCreateDate(new Date());
            game.setGameName(dto.getGameName());
            game.setGameDate(dateFormat.parse(dto.getGameDate()));

            MultipartFile file = dto.getImageFile();
            if (file != null) {
                String urlPrefix = baseUrl + "/files/";
                String fileName = new FileUtilizer().generateFileName(file.getOriginalFilename());
                String fileLocation = archivePath + "/" + fileName;
                String fileUrl = urlPrefix + fileName;
                if (!new FileUtilizer().writeToDisk(file, Paths.get(archivePath), fileName))
                    throw new Exception("File Writing Error Occurred!");
                else {
                    game.setImageUrl(fileUrl);
                    game.setImageFile(fileLocation);
                }
            }

            gameRepository.save(game);

            return new ResponseDto(null, AppConstants.CODE_200, "Game has been saved.");
        } catch (Exception ex) {
            return new ResponseDto(null, AppConstants.CODE_500, AppConstants.SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseDto updateGame(GameDto dto) {
        try {
            Game game = gameRepository.findById(dto.getGameId()).get();

            if (game == null || dto.getGameName() == null || dto.getGameDate() == null) {
                return new ResponseDto(null, AppConstants.CODE_500, "Something is wrong!");
            }
            game.setGameName(dto.getGameName());
            game.setGameDate(dateTimeFormat.parse(dto.getGameDate()));
            MultipartFile file = dto.getImageFile();
            if (file != null) {
                String urlPrefix = baseUrl + "/files/";
                String fileName = new FileUtilizer().generateFileName(file.getOriginalFilename());
                String fileLocation = archivePath + "/" + fileName;
                String fileUrl = urlPrefix + fileName;
                if (!new FileUtilizer().writeToDisk(file, Paths.get(archivePath), fileName))
                    throw new Exception("File Writing Error Occurred!");
                else {
                    game.setImageUrl(fileUrl);
                    game.setImageFile(fileLocation);
                }
            }

            gameRepository.save(game);

            return new ResponseDto(null, AppConstants.CODE_200, "Game has been updated.");
        } catch (Exception ex) {
            return new ResponseDto(null, AppConstants.CODE_500, AppConstants.SERVER_ERROR);
        }
    }

    @Override
    public ResponseDto deleteByGameId(Long id) {
        try {
            gameRepository.deleteById(id);
            return new ResponseDto(null, AppConstants.CODE_200, "Game has been deleted.");
        } catch (Exception ex) {
            return new ResponseDto(null, AppConstants.CODE_500, AppConstants.SERVER_ERROR);
        }
    }

    @Override
    public ResponseDto getAll() {
        try {
            List<Game> games = gameRepository.getAllGames();
            return new ResponseDto(null, AppConstants.CODE_200, games);
        } catch (Exception ex) {
            return new ResponseDto(null, AppConstants.CODE_500, AppConstants.SERVER_ERROR);
        }
    }
}
