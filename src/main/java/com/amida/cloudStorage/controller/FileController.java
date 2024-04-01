package com.amida.cloudStorage.controller;

import io.minio.errors.MinioException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private MinioService minIOService;


    @PostMapping("/{userId}/upload")
    public String uploadFile(@PathVariable String userId, @RequestParam("file") MultipartFile file, @RequestParam("object-name") String objectName) {
        try {
            minIOService.uploadFile(userId, file, objectName);
            return "File uploaded successfully!";
        } catch (MinioException e) {
            return "Error uploading file: " + e.getMessage();
        }
    }

    @DeleteMapping("/{userId}/delete")
    public String deleteFile(@PathVariable String userId, @RequestParam("fileName") String fileName) {
        try {
            minIOService.deleteFile(userId, fileName);
            return "File deleted successfully!";
        } catch (MinioException e) {
            return "Error deleting file: " + e.getMessage();
        }
    }
}

