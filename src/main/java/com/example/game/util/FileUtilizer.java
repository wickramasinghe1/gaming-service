package com.example.game.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class FileUtilizer {


    public boolean writeToDisk(MultipartFile file, Path dir, String fileName) {
        try {
            Path filepath = Paths.get(dir.toString(), fileName);

            try (OutputStream os = Files.newOutputStream(filepath)) {
                os.write(file.getBytes());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateFileName(String originalFileName) {
        return new Random().nextInt(100000) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(originalFileName);
    }
}
