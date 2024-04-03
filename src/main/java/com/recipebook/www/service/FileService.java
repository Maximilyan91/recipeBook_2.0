package com.recipebook.www.service;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean createDataFIle();
}
