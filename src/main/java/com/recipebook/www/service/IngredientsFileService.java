package com.recipebook.www.service;

public interface IngredientsFileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean createDataFIle();
}
