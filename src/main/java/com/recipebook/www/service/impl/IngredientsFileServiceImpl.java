package com.recipebook.www.service.impl;

import com.recipebook.www.service.IngredientsFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientsFileServiceImpl implements IngredientsFileService {
    @Value("${path.to.ingredient.data.file}")
    private String dataFilePath;
    @Value("${name.of.ingredient.data.file}")
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
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
