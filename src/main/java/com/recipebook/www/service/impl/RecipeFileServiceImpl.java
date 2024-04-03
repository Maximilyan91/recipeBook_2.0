package com.recipebook.www.service.impl;

import com.recipebook.www.service.FileService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RecipeFileServiceImpl implements FileService {
    @Value("${path.to.recipe.data.file}")
    private String dataFilePath;
    @Value("${name.of.recipe.data.file}")
    private String dataFileName;

    @Override
    public boolean saveToFile(String json) {
        Path path = Path.of(dataFilePath, dataFileName);
        if (!Files.exists(path)) {
            createDataFIle();
        }
        try {
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFile() {
        try {
           return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createDataFIle() {
        try {
            Files.createFile(Path.of(dataFilePath, dataFileName));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
