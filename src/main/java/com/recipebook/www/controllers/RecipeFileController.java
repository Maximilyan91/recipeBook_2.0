package com.recipebook.www.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RestController
@RequestMapping("/files/recipes")
public class RecipeFileController {
    @Value("${path.to.recipe.data.file}")
    private String filePath;
    @Value("${name.of.recipe.data.file}")
    private String fileName;

    @GetMapping(value = "/download")
    public void downloadFile(HttpServletResponse response) {
        Path path = Path.of(filePath, fileName);

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream()
        ) {
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setContentLengthLong(Files.size(path));
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes_download\" ");
            is.transferTo(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadDataFile(@RequestParam MultipartFile file) throws IOException {
        Path path = Path.of(filePath, fileName);
        Files.deleteIfExists(path);

        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(path, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        return ResponseEntity.ok().build();
    }



}
