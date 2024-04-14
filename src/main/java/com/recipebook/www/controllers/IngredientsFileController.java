package com.recipebook.www.controllers;

import com.recipebook.www.service.impl.IngredientsFileServiceImpl;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/files")
public class IngredientsFileController {

    private final IngredientsFileServiceImpl fileService;


    public IngredientsFileController(IngredientsFileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws FileNotFoundException {
        File file = fileService.getDataFile();

        if (!file.exists()) {
            return ResponseEntity.noContent().build();
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(file.length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"all_ingredients\"")
                .body(resource);
    }
}
